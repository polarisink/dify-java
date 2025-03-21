package com.github.polarisink.dify.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.polarisink.dify.enums.DifySearchMethodEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;


/**
 *
 */
@Getter
@Builder
@ToString
public class DifyRetrievalModelRequest {
    /**
     * 检索方法，必填
     */
    @JsonProperty("search_method")
    private DifySearchMethodEnum searchMethod;
    /**
     * todo 完善
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
    private DifyRerankingModelRequest rerankingModel;
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

    public static DifyRetrievalModelRequestBuilder builder() {
        return new DifyRetrievalModelRequestBuilder() {
            @Override
            public DifyRetrievalModelRequest build() {
                DifyRetrievalModelRequest build = super.build();
                Assert.notNull(build.searchMethod, "检索方法必填");
                return build;
            }
        };
    }

    /**
     * @param rerankingProviderName Rerank 模型提供商
     * @param rerankingModelName    Rerank 模型名称
     */
    public record DifyRerankingModelRequest(@JsonProperty("reranking_provider_name")
                                            String rerankingProviderName, @JsonProperty("reranking_model_name")
                                            String rerankingModelName) {
    }
}
