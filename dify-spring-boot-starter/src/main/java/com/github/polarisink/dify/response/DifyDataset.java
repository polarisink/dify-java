package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.github.polarisink.dify.DifyDatasetBasicInfo;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 知识库实体，新增和分页返回使用
 */
@ToString
@Getter
@SuperBuilder
public final class DifyDataset extends DifyDatasetBasicInfo {
    /**
     * id，新增时没有该字段
     */
    private String id;
    @JsonAlias("data_source_type")
    private String dataSourceType;
    @JsonAlias("app_count")
    private Integer appCount;
    @JsonAlias("document_count")
    private Integer documentCount;
    @JsonAlias("word_count")
    private Integer wordCount;
    @JsonAlias("created_by")
    private String createdBy;
    @JsonAlias("created_at")
    private Integer createdAt;
    @JsonAlias("updated_by")
    private String updatedBy;
    @JsonAlias("updated_at")
    private Integer updatedAt;
    @JsonAlias("embedding_model")
    private String embeddingModel;
    @JsonAlias("embedding_model_provider")
    private String embeddingModelProvider;
    @JsonAlias("embedding_available")
    private String embeddingAvailable;
}