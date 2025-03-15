package com.github.polarisink.dify;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.polarisink.dify.api.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;

import static com.github.polarisink.dify.DifyConsts.KEY_PREFIX;

/**
 * dify-api配置
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(DifyProperties.class)
public class DifyApiAutoConfiguration {
    private final DifyProperties difyProperties;
    private final ObjectMapper objectMapper;

    /**
     * dify聊天api
     *
     * @return 想定远程服务
     */
    @Bean
    @ConditionalOnProperty(prefix = "dify", name = "chat-key")
    public DifyChatApi difyChatApi() {
        return HttpInterfaceUtil.createRestService(difyProperties.getBaseUrl(), objectMapper, difyInterceptor(difyProperties.getChatKey()), DifyChatApi.class);
    }

    /**
     * dify知识库api
     *
     * @return 想定远程服务
     */
    @Bean
    @ConditionalOnProperty(prefix = "dify", name = "dataset-key")
    public DifyDatasetApi difyDatasetApi() {
        return HttpInterfaceUtil.createRestService(difyProperties.getBaseUrl(), objectMapper, difyInterceptor(difyProperties.getDatasetKey()), DifyDatasetApi.class);
    }

    /**
     * dify知识库api
     *
     * @return 想定远程服务
     */
    @Bean
    @ConditionalOnProperty(prefix = "dify", name = "text-key")
    public DifyTextApi difyTextApi() {
        return HttpInterfaceUtil.createRestService(difyProperties.getBaseUrl(), objectMapper, difyInterceptor(difyProperties.getTextKey()), DifyTextApi.class);
    }

    /**
     * dify流程api
     *
     * @return 想定远程服务
     */
    @Bean
    @ConditionalOnProperty(prefix = "dify", name = "workflow-key")
    public DifyWorkflowApi difyWorkflowApi() {
        return HttpInterfaceUtil.createRestService(difyProperties.getBaseUrl(), objectMapper, difyInterceptor(difyProperties.getWorkflowKey()), DifyWorkflowApi.class);
    }

    /**
     * 声明流调用的api
     *
     * @return
     */
    @Bean
    public DifySseApi difySseApi() {
        return HttpInterfaceUtil.createWebService(difyProperties.getBaseUrl(), objectMapper, null, DifySseApi.class);
    }


    /**
     * 增加token的拦截器
     *
     * @param token token
     * @return interceptor
     */
    private ClientHttpRequestInterceptor difyInterceptor(String token) {
        return (request, body, execution) -> {
            request.getHeaders().add(HttpHeaders.AUTHORIZATION, KEY_PREFIX + token);
            return execution.execute(request, body);
        };
    }
}
