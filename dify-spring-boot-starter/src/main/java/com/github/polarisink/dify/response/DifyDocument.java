package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.github.polarisink.dify.enums.DifyDocFormEnum;
import com.github.polarisink.dify.enums.DifyDocTypeEnum;

import java.time.Instant;

/**
 * dify文档
 *
 * @param id
 * @param position
 * @param dataSourceType
 * @param dataSourceInfo
 * @param datasetProcessRuleId
 * @param name
 * @param createdFrom
 * @param createdBy
 * @param createdAt
 * @param tokens
 * @param indexingStatus
 * @param error
 * @param enabled
 * @param disabledAt
 * @param disabledBy
 * @param archived
 * @param docType
 * @param wordCount
 * @param hitCount
 * @param docForm
 */
public record DifyDocument(String id, int position, @JsonAlias("data_source_type") String dataSourceType,
                           @JsonAlias("data_source_info") DataSourceInfo dataSourceInfo,
                           @JsonAlias("dataset_process_rule_id") Long datasetProcessRuleId, String name,
                           @JsonAlias("created_from") String createdFrom, @JsonAlias("created_by") String createdBy,
                           @JsonAlias("createdAt") Instant createdAt, Integer tokens,
                           @JsonAlias("indexing_status") String indexingStatus, Exception error, Boolean enabled,
                           @JsonAlias("disabled_at") Instant disabledAt, @JsonAlias("disabled_by") String disabledBy,
                           Boolean archived, String display_status, @JsonAlias("doc_type") DifyDocTypeEnum docType,
                           @JsonAlias("work_count") Integer wordCount, @JsonAlias("hit_count") Integer hitCount,
                           @JsonAlias("doc_form") DifyDocFormEnum docForm) {
    /**
     * @param upload_file_id
     */
    public record DataSourceInfo(String upload_file_id) {
    }
}