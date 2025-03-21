package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Collection;

public record DifyIndexingStatus(Collection<IndexingStatusItem> data) {
    public record IndexingStatusItem(
            String id,
            @JsonAlias("indexing_status") String indexingStatus,
            @JsonAlias("processing_started_at") Float processingStartedAt,
            @JsonAlias("parsing_completed_at") Float parsingCompletedAt,
            @JsonAlias("cleaning_completed_at") Float cleaningCompletedAt,
            @JsonAlias("splitting_completed_at") Float splittingCompletedAt,
            @JsonAlias("completed_at") Integer completedAt,
            @JsonAlias("paused_at") Integer pausedAt,
            @JsonAlias("stopped_at") Integer stoppedAt,
            @JsonAlias("completed_segments") Integer completedSegments,
            @JsonAlias("total_segments") Integer totalSegments,
            String error
    ) {
    }
}
