package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Collection;

public record DifySseMetaData(DifyUsage usage,
                              @JsonAlias("retriever_resources") Collection<RetrieverResource> retrieverResources) {

    public record RetrieverResource(String position, @JsonAlias("dataset_id") String datasetId,
                                    @JsonAlias("dataset_name") String datasetName,
                                    @JsonAlias("document_id") String documentId,
                                    @JsonAlias("document_name") String documentName,
                                    @JsonAlias("segment_id") String segmentId, Double score, String content) {
    }
}