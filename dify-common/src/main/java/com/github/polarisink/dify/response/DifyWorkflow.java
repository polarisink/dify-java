package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DifyWorkflow(@JsonProperty("workflow_run_id") String workflowRunId,
                           @JsonProperty("task_id") String taskId, DifyWorkflowData data) {

}
