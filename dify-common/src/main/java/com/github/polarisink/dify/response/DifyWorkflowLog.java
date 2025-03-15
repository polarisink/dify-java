package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DifyWorkflowLog(String id,
                              @JsonProperty("workflow_run") DifyWorkflowData workflowRun,
                              @JsonProperty("created_from") String createdFrom,
                              @JsonProperty("created_by_role") String createdByRole,
                              @JsonProperty("created_by_account") String createdByAccount,
                              @JsonProperty("created_by_end_user") DifyUser createdByEndUser,
                              @JsonProperty("created_at") DifyUser createdAt

) {
    public record DifyUser(
            String id, String type,
            @JsonProperty("is_anonymous") String anonymous,
            @JsonProperty("session_id") String sessionId
    ) {
    }
}