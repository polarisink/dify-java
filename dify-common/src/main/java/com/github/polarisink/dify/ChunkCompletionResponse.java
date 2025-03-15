package com.github.polarisink.dify;

import com.github.polarisink.dify.response.DifyUsage;

public record ChunkCompletionResponse(
        String task_id,
        String message_id,
        String answer,
        Object metadata,
        DifyUsage usage,
        String code,
        String message,
        int status
) {
}