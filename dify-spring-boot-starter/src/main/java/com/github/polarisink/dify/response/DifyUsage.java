package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DifyUsage(
        @JsonProperty("prompt_tokens") Integer promptTokens,
        @JsonProperty("prompt_unit_price") String promptUnitPrice,
        @JsonProperty("prompt_price_unit") String promptPriceUnit,
        @JsonProperty("prompt_price") String promptPrice,
        @JsonProperty("completion_tokens") Integer completionTokens,
        @JsonProperty("completion_unit_price") Integer completionUnitPrice,
        @JsonProperty("completion_price_unit") Integer completionPriceUnit,
        @JsonProperty("completion_price") String completionPrice,
        @JsonProperty("total_tokens") Integer totalTokens,
        @JsonProperty("total_price") String totalPrice,
        String currency,
        Double latency
) {
}