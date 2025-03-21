package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DifyUsage(
        @JsonAlias("prompt_tokens") Integer promptTokens,
        @JsonAlias("prompt_unit_price") Float promptUnitPrice,
        @JsonAlias("prompt_price_unit") Float promptPriceUnit,
        @JsonAlias("prompt_price") Float promptPrice,
        @JsonAlias("completion_tokens") Integer completionTokens,
        @JsonAlias("completion_unit_price") Float completionUnitPrice,
        @JsonAlias("completion_price_unit") Float completionPriceUnit,
        @JsonAlias("completion_price") Float completionPrice,
        @JsonAlias("total_tokens") Integer totalTokens,
        @JsonAlias("total_price") Float totalPrice,
        String currency,
        Double latency
) {
}