package com.github.polarisink.dify.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.polarisink.dify.core.HttpInterfaceUtil;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.polarisink.dify.core.HttpInterfaceUtil.resolveToken;

/**
 * dify基础client
 */
abstract class AbstractDifyClient {

    protected final RestClient restClient;
    protected final WebClient webClient;

    /**
     * 自定义client方法
     *
     * @param restClient restClient
     * @param webClient  webClient
     */
    public AbstractDifyClient(RestClient restClient, WebClient webClient) {
        Assert.notNull(restClient, "restClient can not be null");
        Assert.notNull(webClient, "webClient can not be null");
        this.restClient = restClient;
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
        Assert.hasText(baseUrl, "dify baseUrl can not be blank");
        //对baseUrl进行基础校验
        Assert.isTrue(baseUrl.startsWith("http://") || baseUrl.startsWith("https://"), "dify baseUrl is invalid");
        //对token进行基础校验
        Assert.hasText(token, "dify token can not be blank");
        //token有头就不处理，否则加一个头
        String authorization = resolveToken(token);
        this.restClient = HttpInterfaceUtil.createRestClient(baseUrl, authorization, objectMapper, interceptor);
        this.webClient = HttpInterfaceUtil.createWebClient(baseUrl, authorization, objectMapper, filter);
    }
}
