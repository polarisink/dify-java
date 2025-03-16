package com.github.polarisink.dify.core;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * dify基础配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "dify")
public class DifyProperties implements InitializingBean {
    /**
     * 地址
     */
    private String baseUrl;
    /**
     * 聊天室的api-key
     */
    private String chatKey;
    /**
     * 知识库的api-key
     */
    private String datasetKey;
    /**
     * 文本的api-key
     */
    private String textKey;
    /**
     * 工作流的api-key
     */
    private String workflowKey;

    @Override
    public void afterPropertiesSet() {
        if (baseUrl == null || baseUrl.isBlank() || !baseUrl.startsWith("http://") || !baseUrl.endsWith("/v1")) {
            throw new IllegalArgumentException("dify baseUrl is invalid");
        }
    }
}
