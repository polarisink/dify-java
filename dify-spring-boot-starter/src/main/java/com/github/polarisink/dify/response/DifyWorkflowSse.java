package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public record DifyWorkflowSse(String event, @JsonAlias("message_id") String messageId,
                              @JsonAlias("conversion_id") String conversionId,
                              @JsonAlias("workflow_run_id") String workflowRunId,
                              @JsonAlias("created_at") Integer createdAt, @JsonAlias("task_id") String taskId,
                              String answer, DifySseMetaData metadata, String audio,
                              JsonNode data
) {

    public record WorkflowStarted(String id, @JsonAlias("workflow_id") String workflowId,
                                  @JsonAlias("sequence_number") String sequenceNumber,
                                  @JsonAlias("created_at") Integer createdAt) {
    }

    public record NodeStarted(String id,
                              @JsonAlias("node_id") String nodeId,
                              @JsonAlias("node_type") String nodeType,
                              String title, Integer index,
                              @JsonAlias("predecessor_node_id") String predecessorNodeId,
                              Map<String, Object> inputs,
                              @JsonAlias("created_at") Integer createdAt) {
    }

    public record NodeFinished(String id,
                               @JsonAlias("node_id") String nodeId,
                               @JsonAlias("node_type") String nodeType,
                               String title, Integer index,
                               @JsonAlias("predecessor_node_id") String predecessorNodeId,
                               Map<String, Object> inputs,
                               Map<String, Object> outputs,
                               String status,
                               @JsonAlias("elapsed_time") Float elapsedTime,
                               @JsonAlias("Execution_metadata") ExecutionMetadata executionMetadata,
                               @JsonAlias("created_at") Integer createdAt) {


    }

    public record ExecutionMetadata(@JsonAlias("total_tokens") Integer totalTokens,
                                    @JsonAlias("total_price") Float totalPrice, String currency) {
    }

    public record WorkflowFinished(String id, @JsonAlias("workflow_run_id") String workflowRunId,
                                   Map<String, Object> outputs, String status,
                                   @JsonAlias("elapsed_time") Float elapsedTime,
                                   @JsonAlias("total_tokens") Integer totalTokens,
                                   @JsonAlias("total_price") Float totalPrice, String currency,
                                   @JsonAlias("finished_at") Integer finishedAt) {

    }

}