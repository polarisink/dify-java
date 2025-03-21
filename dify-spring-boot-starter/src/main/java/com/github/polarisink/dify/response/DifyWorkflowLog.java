package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DifyWorkflowLog(String id,
                              @JsonAlias("workflow_run") DifyWorkflowData workflowRun,
                              @JsonAlias("created_from") String createdFrom,
                              @JsonAlias("created_by_role") String createdByRole,
                              @JsonAlias("created_by_account") String createdByAccount,
                              @JsonAlias("created_by_end_user") DifyUser createdByEndUser,
                              @JsonAlias("created_at") DifyUser createdAt

) {
    public record DifyUser(
            String id, String type,
            @JsonAlias("is_anonymous") String anonymous,
            @JsonAlias("session_id") String sessionId
    ) {
    }
}