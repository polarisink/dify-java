package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DifyWorkflow(@JsonAlias("workflow_run_id") String workflowRunId,
                           @JsonAlias("task_id") String taskId, DifyWorkflowData data) {

}
