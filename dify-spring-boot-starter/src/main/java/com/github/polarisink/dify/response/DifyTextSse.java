package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DifyTextSse(String id, String answer, String event,
                          @JsonAlias("conversion_id") String conversionId,
                          @JsonAlias("created_at") Integer createdAt,
                          @JsonAlias("task_id") String taskId,
                          @JsonAlias("message_id") String messageId,
                          String audio
) {
}
