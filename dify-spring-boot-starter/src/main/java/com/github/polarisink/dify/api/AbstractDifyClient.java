package com.github.polarisink.dify.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.polarisink.dify.core.HttpInterfaceUtil;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * dify基础client
 */
abstract class AbstractDifyClient {
    private static final String KEY_PREFIX = "Bearer ";

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
        this.restClient = HttpInterfaceUtil.createRestClient(baseUrl, authorization, objectMapper, interceptor);
        this.webClient = HttpInterfaceUtil.createWebClient(baseUrl, authorization, objectMapper, filter);
    }
}
