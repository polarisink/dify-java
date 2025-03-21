package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DifyTextChat(String id, String answer, @JsonAlias("created_at") Integer createdAt) {
}
