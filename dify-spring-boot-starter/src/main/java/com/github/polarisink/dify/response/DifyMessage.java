package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * @param id
 * @param conversionId
 * @param inputs
 * @param query
 * @param answer
 */
public record DifyMessage(String id, @JsonProperty("conversion_id") String conversionId, Map<String, Object> inputs,
                          String query, String answer) {

}