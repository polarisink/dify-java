package com.github.polarisink.dify.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.polarisink.dify.DifyDatasetBasicInfo;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 创建空知识库请求
 */
@Getter
@SuperBuilder
@ToString(callSuper = true)
public final class DifyDatasetCreateRequest extends DifyDatasetBasicInfo {

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

    public static DifyDatasetCreateRequestBuilder builder() {
        return new DifyDatasetCreateRequestBuilder() {

            @Override
            public DifyDatasetCreateRequest build() {
                return new DifyDatasetCreateRequest(this);
            }

            @Override
            protected DifyDatasetCreateRequestBuilder self() {
                return this;
            }
        };
    }
}
