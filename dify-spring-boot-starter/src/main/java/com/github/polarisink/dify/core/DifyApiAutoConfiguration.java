package com.github.polarisink.dify.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.polarisink.dify.api.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

import static com.github.polarisink.dify.core.HttpInterfaceUtil.resolveToken;

/**
 * dify-api配置
 */
@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties(DifyProperties.class)
@Conditional(DifyValidationAutoConfiguration.EnableDifyApiCondition.class) // 条件控制

public class DifyApiAutoConfiguration {
    private final DifyProperties difyProperties;
    private final ObjectMapper objectMapper;

    /**
     * dify聊天api
     *
     * @return 想定远程服务
     */
    @Bean
    @ConditionalOnMissingBean(DifyChatApi.class)
    @ConditionalOnProperty(prefix = "dify", name = "chat-key")
    public DifyChatApi difyChatApi() {
        return HttpInterfaceUtil.createRestService(difyProperties.getBaseUrl(), resolveToken(difyProperties.getChatKey()), objectMapper, null, DifyChatApi.class);
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
        return HttpInterfaceUtil.createRestService(difyProperties.getBaseUrl(), resolveToken(difyProperties.getDatasetKey()), objectMapper, null, DifyDatasetApi.class);
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
        return HttpInterfaceUtil.createRestService(difyProperties.getBaseUrl(), resolveToken(difyProperties.getTextKey()), objectMapper, null, DifyTextApi.class);
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
        return HttpInterfaceUtil.createRestService(difyProperties.getBaseUrl(), resolveToken(difyProperties.getWorkflowKey()), objectMapper, null, DifyWorkflowApi.class);
    }

    /**
     * 声明流调用的api
     *
     * @return client
     */
    @Bean
    @ConditionalOnMissingBean(DifyChatSseApi.class)
    public DifyChatSseApi difySseApi() {
        return HttpInterfaceUtil.createWebService(difyProperties.getBaseUrl(), resolveToken(difyProperties.getChatKey()), objectMapper, null, DifyChatSseApi.class);
    }

    /**
     * 声明流调用知识库的api
     *
     * @return client
     */
    @Bean
    @ConditionalOnMissingBean(DifyWorkflowSseApi.class)
    public DifyWorkflowSseApi difyWorkflowSseApi() {
        return HttpInterfaceUtil.createWebService(difyProperties.getBaseUrl(), resolveToken(difyProperties.getChatKey()), objectMapper, null, DifyWorkflowSseApi.class);
    }

}
