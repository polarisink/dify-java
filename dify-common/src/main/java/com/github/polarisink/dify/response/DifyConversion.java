package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Map;

public record DifyConversion(String id, String name, Map<String, Object> inputs, String status,
                             String introduction,
                             @JsonAlias("created_at") Integer createdAt,
                             @JsonAlias("updated_at") Integer updatedAt) {
}
