package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DifyUsage(
        @JsonProperty("prompt_tokens") Integer promptTokens,
        @JsonProperty("prompt_unit_price") Float promptUnitPrice,
        @JsonProperty("prompt_price_unit") Float promptPriceUnit,
        @JsonProperty("prompt_price") Float promptPrice,
        @JsonProperty("completion_tokens") Integer completionTokens,
        @JsonProperty("completion_unit_price") Float completionUnitPrice,
        @JsonProperty("completion_price_unit") Float completionPriceUnit,
        @JsonProperty("completion_price") Float completionPrice,
        @JsonProperty("total_tokens") Integer totalTokens,
        @JsonProperty("total_price") Float totalPrice,
        String currency,
        Double latency
) {
}