package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record DifyWorkflowData(String id, @JsonAlias("workflow_id") String workflowId, String status,
                               Map<String, Object> outputs,
                               String inputs,
                               String error,
                               @JsonAlias("elapsed_time") Float elapsedTime,
                               @JsonAlias("total_tokens") Integer totalTokens,
                               @JsonAlias("total_steps") Integer totalSteps,
                               //todo 这个时间有多种格式很烦
                               @JsonAlias("created_at") Integer createdAt,
                               @JsonAlias("finished_at") Integer finishedAt) {
}