package com.github.polarisink.dify.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.polarisink.dify.enums.DifySearchMethodEnum;
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
    private final RetrievalModel retrievalModel;

    @Builder
    @Getter
    @ToString
    public static class RetrievalModel {
        /**
         * 检索方法，必填
         */
        @JsonProperty("search_method")
        private DifySearchMethodEnum searchMethod;
        /**
         * 是否启用 Reranking，非必填，如果检索模式为 semantic_search 模式或者 hybrid_search 则传值
         */
        @JsonProperty("reranking_enable")
        private Boolean rerankingEnable;
        /**
         * Rerank 模型配置，非必填，如果启用了 reranking 则传值
         */
        @JsonProperty("reranking_mode")
        private Object rerankingMode;
        /**
         * Rerank 模型配置
         */
        @JsonProperty("reranking_model")
        private DifyRerankingModel rerankingModel;
        /**
         * 混合检索模式下语意检索的权重设置
         */
        private Float weights;
        /**
         * 返回结果数量，非必填
         */
        @JsonProperty("top_k")
        private Integer topK;
        /**
         * 是否开启 score 阈值
         */
        @JsonProperty("score_threshold_enabled")
        private Boolean scoreThresholdEnabled;
        /**
         * Score 阈值
         */
        @JsonProperty("score_threshold")
        private Float scoreThreshold;

    }

    /**
     * 模型提供
     */
    @Builder
    @Getter
    @ToString
    public static class DifyRerankingModel {
        /**
         * Rerank 模型提供商
         */
        @JsonProperty("reranking_provider_name")
        private String rerankingProviderName;
        /**
         * Rerank 模型名称
         */
        @JsonProperty("reranking_model_name")
        private String rerankingModelName;
    }

}