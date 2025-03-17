package com.github.polarisink.dify.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * dify基础配置
 */
@Data
@ConfigurationProperties(prefix = "dify")
public class DifyProperties {
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

}
