package com.github.polarisink.dify.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * dify检索请求
 */
@Builder
@Getter
@ToString
public final class DifyRetrieveRequest {
    /**
     * 检索关键词
     */
    private final String query;
    /**
     * 检索参数（选填，如不填，按照默认方式召回）
     */
    @JsonProperty("retrieval_model")
    private final DifyRetrievalModelRequest retrievalModel;


}