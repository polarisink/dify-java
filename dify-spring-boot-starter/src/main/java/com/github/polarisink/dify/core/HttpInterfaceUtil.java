package com.github.polarisink.dify.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

/**
 * springboot的http interface工具类
 */
@Slf4j
public class HttpInterfaceUtil {

    private static final String KEY_PREFIX = "Bearer ";

    /**
     * 创建RestClient
     *
     * @param baseUrl      基础地址
     * @param token        token
     * @param objectMapper jackson
     * @param interceptor  拦截器
     * @return RestClient
     */
    public static RestClient createRestClient(String baseUrl, String token, ObjectMapper objectMapper, ClientHttpRequestInterceptor interceptor) {
        RestClient.Builder builder = RestClient.builder().baseUrl(baseUrl);
        if (token != null && !token.isBlank()) {
            builder.defaultHeader(HttpHeaders.AUTHORIZATION, token);
        }
        if (objectMapper != null) {
            builder.messageConverters(list -> {
                list.removeIf(c -> c.getClass().getName().equals(MappingJackson2HttpMessageConverter.class.getName()));
                MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
                converter.setObjectMapper(objectMapper);
                list.add(0, converter);
            });
        }
        if (interceptor != null) {
            builder.requestInterceptor(interceptor);
        }
        return builder.build();
    }

    /**
     * 创建RestClient
     *
     * @param baseUrl      基础地址
     * @param token        token
     * @param objectMapper jackson
     * @param function     过滤器
     * @return WebClient
     */
    public static WebClient createWebClient(String baseUrl, String token, ObjectMapper objectMapper, ExchangeFilterFunction function) {
        WebClient.Builder builder = WebClient.builder().baseUrl(baseUrl);
        if (token != null && !token.isBlank()) {
            builder.defaultHeader(HttpHeaders.AUTHORIZATION, token);
        }
        if (objectMapper != null) {
            // 创建自定义编解码策略
            ExchangeStrategies strategies = ExchangeStrategies.builder().codecs(configurer -> {
                // 移除默认的 Jackson 编解码器
                configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper, MediaType.APPLICATION_JSON));
                configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper, MediaType.APPLICATION_JSON));
            }).build();
            builder.exchangeStrategies(strategies);
        }
        if (function != null) {
            builder.filter(function);
        }
        return builder.build();
    }

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
    public static <T> T createRestService(String baseUrl, String token, ObjectMapper objectMapper, ClientHttpRequestInterceptor interceptor, Class<T> clazz) {
        RestClient restClient = createRestClient(baseUrl, token, objectMapper, interceptor);
        return HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build().createClient(clazz);
    }

    /**
     * 通过WebClient创建代理服务
     *
     * @param baseUrl      基本地址
     * @param objectMapper jackson
     * @param function     拦截器
     * @param clazz        代理类class
     * @param <T>          泛型
     * @return 代理类
     */
    public static <T> T createWebService(String baseUrl, String token, ObjectMapper objectMapper, ExchangeFilterFunction function, Class<T> clazz) {
        WebClient webClient = createWebClient(baseUrl, token, objectMapper, function);
        // 构建 WebClient 并配置策略
        return HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build().createClient(clazz);
    }

    /**
     * 处理token，看情况追加前缀
     *
     * @param token token
     * @return 处理后的token
     */
    public static String resolveToken(String token) {
        return token.startsWith(KEY_PREFIX) ? token : KEY_PREFIX + token;
    }
}
