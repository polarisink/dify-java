package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * dify文段
 *
 * @param id
 * @param position
 * @param documentId
 * @param content
 * @param answer
 * @param wordCount
 * @param tokens
 * @param keywords
 * @param indexNodeId
 * @param indexNodeHash
 * @param hitCount
 * @param enabled
 * @param disabledAt
 * @param disabledBy
 * @param status
 * @param createdBy
 * @param createdAt
 * @param indexingAt
 * @param completedAt
 * @param error
 * @param stoppedAt
 * @param document
 */
public record DifySegment(String id, Integer position, @JsonAlias("document_id") String documentId, String content,
                          String answer, @JsonAlias("word_count") Integer wordCount, Integer tokens,
                          Collection<String> keywords, @JsonAlias("index_node_id") String indexNodeId,
                          @JsonAlias("index_node_hash") String indexNodeHash, @JsonAlias("hit_count") Integer hitCount,
                          Boolean enabled, @JsonAlias("disabled_at") LocalDateTime disabledAt,
                          @JsonAlias("disabled_by") String disabledBy, String status,
                          @JsonAlias("created_by") String createdBy, @JsonAlias("created_at") Long createdAt,
                          @JsonAlias("indexing_at") Long indexingAt, @JsonAlias("completed_at") Long completedAt,
                          String error, @JsonAlias("stopped_at") LocalDateTime stoppedAt, DifyDocument document) {
}