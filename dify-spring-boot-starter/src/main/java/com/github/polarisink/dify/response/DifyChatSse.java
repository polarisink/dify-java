package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Collection;

public record DifyChatSse(String event, String id, @JsonAlias("message_id") String messageId,
                          @JsonAlias("conversion_id") String conversionId, @JsonAlias("created_at") Integer createdAt,
                          @JsonAlias("task_id") String taskId, Integer position, String thought, String observation,
                          String tool, @JsonAlias("tool_input") String toolInput,
                          @JsonAlias("message_files") Collection<String> messageFiles,
                          String type, @JsonAlias("belongs_to") String belongsTo, String url, String answer,
                          DifySseMetaData metadata, String audio) {


}