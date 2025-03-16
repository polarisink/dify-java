package com.github.polarisink.dify.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.polarisink.dify.DifyConsts.KEY_PREFIX;

/**
 * dify基础client
 */
abstract class AbstractDifyClient {

    protected final RestClient restClient;
    protected final WebClient webClient;

    /**
     * 自定义构造方法
     * <p>
     * 当下面的方法不够用的时候可以自定义进行使用
     *
     * @param restClient restClient
     * @param webClient  webClient
     */
    public AbstractDifyClient(RestClient restClient, WebClient webClient) {
        if (restClient == null) {
            throw new IllegalArgumentException("restClient can not be null");
        }
        this.restClient = restClient;
        //webclient不做校验，因为只有少数接口使用webclient
        this.webClient = webClient;
    }

    /**
     * 构造方法
     *
     * @param baseUrl      dify baseUrl
     * @param token        dify token
     * @param objectMapper objectMapper
     * @param interceptor  interceptor
     * @param filter       filter
     */
    public AbstractDifyClient(String baseUrl, String token, ObjectMapper objectMapper, ClientHttpRequestInterceptor interceptor, ExchangeFilterFunction filter) {
        //对baseUrl进行基础校验
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalArgumentException("dify baseUrl can not be blank");
        }
        //对baseUrl进行基础校验
        if (!baseUrl.startsWith("http://") && !baseUrl.startsWith("https://")) {
            throw new IllegalArgumentException("dify baseUrl is invalid");
        }
        //对token进行基础校验
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("dify token can not be blank");
        }
        //token有头就不处理，否则加一个头
        String authorization = token.startsWith(KEY_PREFIX) ? token : KEY_PREFIX + token;
        RestClient.Builder restClientBuilder = RestClient.builder().baseUrl(baseUrl).defaultHeader(HttpHeaders.AUTHORIZATION, authorization);
        WebClient.Builder webClientBuilder = WebClient.builder().baseUrl(baseUrl).defaultHeader(HttpHeaders.AUTHORIZATION, authorization);
        if (objectMapper != null) {
            restClientBuilder.messageConverters(list -> {
                list.removeIf(c -> c.getClass().getName().equals(MappingJackson2HttpMessageConverter.class.getName()));
                MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
                converter.setObjectMapper(objectMapper);
                list.add(0, converter);
            });
            ExchangeStrategies build = ExchangeStrategies.builder().codecs(configurer -> {
                configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
                configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
            }).build();
            webClientBuilder.exchangeStrategies(build);
        }
        if (interceptor != null) {
            restClientBuilder.requestInterceptor(interceptor);
        }
        if (filter != null) {
            webClientBuilder.filter(filter);
        }
        this.restClient = restClientBuilder.build();
        this.webClient = webClientBuilder.build();
    }
}
