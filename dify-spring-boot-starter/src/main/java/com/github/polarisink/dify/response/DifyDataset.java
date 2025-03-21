package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.github.polarisink.dify.enums.DifyDocFormEnum;
import com.github.polarisink.dify.enums.DifyIndexTechniqueEnum;
import com.github.polarisink.dify.enums.DifyPermissionEnum;
import com.github.polarisink.dify.enums.DifySearchMethodEnum;

import java.util.Collection;

/**
 * 知识库实体，新增和分页返回使用
 *
 * @param name              知识库名称
 * @param description       知识库描述
 * @param permission        权限
 * @param provider          Provider（选填，默认 vendor）
 * @param indexingTechnique 索引模式（选填，建议填写）
 * @param id                id，新增时没有该字段
 */
public record DifyDataset(@JsonAlias("doc_form") DifyDocFormEnum docForm, String name, String description,
                          DifyPermissionEnum permission, String provider,
                          @JsonAlias("indexing_technique") DifyIndexTechniqueEnum indexingTechnique, String id,
                          @JsonAlias("data_source_type") String dataSourceType,
                          @JsonAlias("app_count") Integer appCount, @JsonAlias("document_count") Integer documentCount,
                          @JsonAlias("word_count") Integer wordCount, @JsonAlias("created_by") String createdBy,
                          @JsonAlias("created_at") Integer createdAt, @JsonAlias("updated_by") String updatedBy,
                          @JsonAlias("updated_at") Integer updatedAt,
                          @JsonAlias("embedding_model") String embeddingModel,
                          @JsonAlias("embedding_model_provider") String embeddingModelProvider,
                          @JsonAlias("embedding_available") String embeddingAvailable,
                          @JsonAlias("retrieval_model_dict") DifyRetrievalModel retrievalModelDict,
                          @JsonAlias("external_retrieval_model") DifyRetrievalModel externalRetrievalModel,
                          Collection<String> tags) {

    /**
     * @param searchMethod          检索方法，必填
     * @param rerankingEnable       是否启用 Reranking，非必填，如果检索模式为 semantic_search 模式或者 hybrid_search 则传值
     * @param rerankingMode         Rerank 模型配置，非必填，如果启用了 reranking 则传值
     * @param rerankingModel        Rerank 模型配置
     * @param weights               混合检索模式下语意检索的权重设置
     * @param topK                  返回结果数量，非必填
     * @param scoreThresholdEnabled 是否开启 score 阈值
     * @param scoreThreshold        Score 阈值
     */
    public record DifyRetrievalModel(@JsonAlias("search_method") DifySearchMethodEnum searchMethod,
                                     @JsonAlias("reranking_enable") Boolean rerankingEnable,
                                     @JsonAlias("reranking_mode") Object rerankingMode,
                                     @JsonAlias("reranking_model") DifyRerankingModel rerankingModel,
                                     Float weights, @JsonAlias("top_k") Integer topK,
                                     @JsonAlias("score_threshold_enabled") Boolean scoreThresholdEnabled,
                                     @JsonAlias("score_threshold") Float scoreThreshold) {

    }

    /**
     * @param rerankingProviderName Rerank 模型提供商
     * @param rerankingModelName    Rerank 模型名称
     */
    public record DifyRerankingModel(@JsonAlias("reranking_provider_name")
                                     String rerankingProviderName, @JsonAlias("reranking_model_name")
                                     String rerankingModelName) {
    }

}