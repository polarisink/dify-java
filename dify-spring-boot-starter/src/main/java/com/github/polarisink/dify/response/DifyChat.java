package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DifyChat(String event, @JsonAlias("message_id") String messageId,
                       @JsonAlias("conversion_id") String conversionId, String mode, String answer,
                       DifyChatMetadata metadata) {
    public record DifyChatMetadata(DifyUsage usage) {

    }


}
