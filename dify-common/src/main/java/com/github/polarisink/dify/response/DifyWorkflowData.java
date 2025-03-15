package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record DifyWorkflowData(String id, @JsonProperty("workflow_id") String workflowId, String status,
                               Map<String, Object> outputs,
                               String inputs,
                               String error,
                               @JsonProperty("elapsed_time") Float elapsedTime,
                               @JsonProperty("total_tokens") Integer totalTokens,
                               @JsonProperty("total_steps") Integer totalSteps,
                               //todo 这个时间有多种格式很烦
                               @JsonProperty("created_at") Integer createdAt,
                               @JsonProperty("finished_at") Integer finishedAt) {
}