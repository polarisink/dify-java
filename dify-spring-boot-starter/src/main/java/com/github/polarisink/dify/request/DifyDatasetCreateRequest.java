package com.github.polarisink.dify.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.polarisink.dify.enums.DifyIndexTechniqueEnum;
import com.github.polarisink.dify.enums.DifyPermissionEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 创建空知识库请求
 */
@Getter
@Builder
@ToString(callSuper = true)
public final class DifyDatasetCreateRequest {
    /**
     * 知识库名称（必填）
     */
    private String name;
    /**
     * 知识库描述（选填）
     */
    private String description;
    /**
     * 权限（选填，默认 only_me）
     */
    @Builder.Default
    private DifyPermissionEnum permission = DifyPermissionEnum.only_me;
    /**
     * Provider（选填，默认 vendor）
     */
    private String provider;
    /**
     * 索引模式（选填，建议填写）
     */
    @JsonAlias("indexing_technique")
    private DifyIndexTechniqueEnum indexingTechnique;

    /**
     * 外部知识库 API_ID（选填）
     */
    @JsonProperty("external_knowledge_api_id")
    private String externalKnowledgeApiId;
    /**
     * 外部知识库 ID（选填）
     */
    @JsonProperty("external_knowledge_id")
    private String externalKnowledgeId;

}
