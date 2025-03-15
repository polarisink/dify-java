package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public record DifyIndexingStatus(Collection<IndexingStatusItem> data) {
    public record IndexingStatusItem(
            String id,
            @JsonProperty("indexing_status") String indexingStatus,
            @JsonProperty("processing_started_at") Float processingStartedAt,
            @JsonProperty("parsing_completed_at") Float parsingCompletedAt,
            @JsonProperty("cleaning_completed_at") Float cleaningCompletedAt,
            @JsonProperty("splitting_completed_at") Float splittingCompletedAt,
            @JsonProperty("completed_at") Integer completedAt,
            @JsonProperty("paused_at") Integer pausedAt,
            @JsonProperty("stopped_at") Integer stoppedAt,
            @JsonProperty("completed_segments") Integer completedSegments,
            @JsonProperty("total_segments") Integer totalSegments,
            String error
    ) {
    }
}
