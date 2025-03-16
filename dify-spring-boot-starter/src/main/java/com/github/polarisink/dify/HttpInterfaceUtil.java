package com.github.polarisink.dify;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

/**
 * springboot的http interface工具类
 */
@Slf4j
public class HttpInterfaceUtil {
    /**
     * 通过restClient创建代理服务
     *
     * @param baseUrl      基本地址
     * @param objectMapper jackson
     * @param interceptor  拦截器
     * @param clazz        代理类class
     * @param <T>          泛型
     * @return 代理类
     */
    public static <T> T createRestService(String baseUrl, ObjectMapper objectMapper, ClientHttpRequestInterceptor interceptor, Class<T> clazz) {
        RestClient.Builder builder = RestClient.builder().baseUrl(baseUrl).defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
            String errorBody = new String(response.getBody().readAllBytes());
            log.error("errorBody:{}", errorBody);
        }).messageConverters(list -> {
            list.removeIf(c -> c.getClass().getName().equals(MappingJackson2HttpMessageConverter.class.getName()));
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
            converter.setObjectMapper(objectMapper);
            list.add(0,converter);
        });
        if (interceptor != null) {
            builder.requestInterceptor(interceptor);
        }
        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(builder.build())).build().createClient(clazz);
    }

    /**
     * 通过restClient创建代理服务
     *
     * @param baseUrl      基本地址
     * @param objectMapper jackson
     * @param function     拦截器
     * @param clazz        代理类class
     * @param <T>          泛型
     * @return 代理类
     */
    public static <T> T createWebService(String baseUrl, ObjectMapper objectMapper, ExchangeFilterFunction function, Class<T> clazz) {
        // 创建自定义编解码策略
        ExchangeStrategies strategies = ExchangeStrategies.builder().codecs(configurer -> {
            // 移除默认的 Jackson 编解码器
            configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
            configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
        }).build();

        // 构建 WebClient 并配置策略
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).filter((request, next) -> {
            for (Map.Entry<String, List<String>> e : request.headers().entrySet()) {
                if (e.getKey().equals(HttpHeaders.AUTHORIZATION)) {
                    List<String> values = e.getValue();
                    if (values != null && !values.isEmpty()) {
                        String s = values.get(0);
                    }
                }
            }
            // 执行请求并处理响应（如统一错误处理）
            return next.exchange(request).flatMap(response -> {
                if (!response.statusCode().is2xxSuccessful()) {
                    return response.bodyToMono(HttpClientErrorException.class).flatMap(err -> Mono.error(new RuntimeException("API 调用失败: " + err.getLocalizedMessage())));
                }
                return Mono.just(response);
            });
        }).exchangeStrategies(strategies).build();
        return HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build().createClient(clazz);
    }
}
