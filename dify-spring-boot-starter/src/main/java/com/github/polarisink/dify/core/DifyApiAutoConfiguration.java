package com.github.polarisink.dify.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.polarisink.dify.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

import static com.github.polarisink.dify.core.HttpInterfaceUtil.resolveToken;

/**
 * dify-api配置
 */
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
     * 声明流调用的chat-api
     *
     * @return client
     */
    @Bean
    @ConditionalOnProperty(prefix = "dify", name = "chat-key")
    @ConditionalOnMissingBean(DifyChatSseApi.class)
    public DifyChatSseApi difyChatSseApi() {
        return HttpInterfaceUtil.createWebService(difyProperties.getBaseUrl(), resolveToken(difyProperties.getChatKey()), objectMapper, null, DifyChatSseApi.class);
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
     * 文本sse-api
     *
     * @return api
     */
    @Bean
    @ConditionalOnMissingBean(DifyChatSseApi.class)
    @ConditionalOnProperty(prefix = "dify", name = "text-key")
    public DifyTextSseApi difyTextSseApi() {
        return HttpInterfaceUtil.createWebService(difyProperties.getBaseUrl(), resolveToken(difyProperties.getTextKey()), objectMapper, null, DifyTextSseApi.class);
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
     * 声明流调用知识库的api
     *
     * @return client
     */
    @Bean
    @ConditionalOnProperty(prefix = "dify", name = "workflow-key")
    @ConditionalOnMissingBean(DifyWorkflowSseApi.class)
    public DifyWorkflowSseApi difyWorkflowSseApi() {
        return HttpInterfaceUtil.createWebService(difyProperties.getBaseUrl(), resolveToken(difyProperties.getWorkflowKey()), objectMapper, null, DifyWorkflowSseApi.class);
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

}
