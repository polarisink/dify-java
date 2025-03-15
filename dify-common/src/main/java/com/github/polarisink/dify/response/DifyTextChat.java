package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DifyTextChat(String id, String answer, @JsonProperty("created_at") Integer createdAt) {
}
