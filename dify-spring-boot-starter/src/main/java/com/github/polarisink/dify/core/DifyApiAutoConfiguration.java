package com.github.polarisink.dify.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.polarisink.dify.api.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

/**
 * dify-api配置
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(DifyProperties.class)
class DifyApiAutoConfiguration {
    private final DifyProperties difyProperties;
    private final ObjectMapper objectMapper;
    @Qualifier("difyInterceptor")
    private final ClientHttpRequestInterceptor interceptor;
    @Qualifier("difyFilter")
    private final ExchangeFilterFunction filter;

    public DifyApiAutoConfiguration(DifyProperties difyProperties, ObjectMapper objectMapper, ClientHttpRequestInterceptor interceptor, ExchangeFilterFunction filter) {
        this.difyProperties = difyProperties;
        this.objectMapper = objectMapper;
        this.interceptor = interceptor;
        this.filter = filter;
    }


    /**
     * dify聊天api
     *
     * @return 想定远程服务
     */
    @Bean
    @ConditionalOnMissingBean(DifyChatApi.class)
    @ConditionalOnProperty(prefix = "dify", name = "chat-key")
    public DifyChatApi difyChatApi() {
        return HttpInterfaceUtil.createRestService(difyProperties.getBaseUrl(), difyProperties.getChatKey(), objectMapper, interceptor, DifyChatApi.class);
    }

    /**
     * dify知识库api
     *
     * @return 想定远程服务
     */
    @Bean
    @ConditionalOnMissingBean(DifyDatasetApi.class)
    @ConditionalOnProperty(prefix = "dify", name = "dataset-key")
    public DifyDatasetApi difyDatasetApi() {
        return HttpInterfaceUtil.createRestService(difyProperties.getBaseUrl(), difyProperties.getDatasetKey(), objectMapper, interceptor, DifyDatasetApi.class);
    }

    /**
     * dify知识库api
     *
     * @return 想定远程服务
     */
    @Bean
    @ConditionalOnMissingBean(DifyTextApi.class)
    @ConditionalOnProperty(prefix = "dify", name = "text-key")
    public DifyTextApi difyTextApi() {
        return HttpInterfaceUtil.createRestService(difyProperties.getBaseUrl(), difyProperties.getTextKey(), objectMapper, interceptor, DifyTextApi.class);
    }

    /**
     * dify流程api
     *
     * @return 想定远程服务
     */
    @Bean
    @ConditionalOnMissingBean(DifyWorkflowApi.class)
    @ConditionalOnProperty(prefix = "dify", name = "workflow-key")
    public DifyWorkflowApi difyWorkflowApi() {
        return HttpInterfaceUtil.createRestService(difyProperties.getBaseUrl(), difyProperties.getWorkflowKey(), objectMapper, interceptor, DifyWorkflowApi.class);
    }

    /**
     * 声明流调用的api
     *
     * @return client
     */
    @Bean
    @ConditionalOnMissingBean(DifyChatSseApi.class)
    public DifyChatSseApi difySseApi() {
        return HttpInterfaceUtil.createWebService(difyProperties.getBaseUrl(), difyProperties.getChatKey(), objectMapper, filter, DifyChatSseApi.class);
    }

    /**
     * 声明流调用知识库的api
     *
     * @return client
     */
    @Bean
    @ConditionalOnMissingBean(DifyWorkflowSseApi.class)
    public DifyWorkflowSseApi difyWorkflowSseApi() {
        return HttpInterfaceUtil.createWebService(difyProperties.getBaseUrl(), difyProperties.getChatKey(), objectMapper, filter, DifyWorkflowSseApi.class);
    }

}
