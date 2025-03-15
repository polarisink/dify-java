package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.math.BigDecimal;
import java.util.List;

/**
 * dify检索结果体
 *
 * @param query
 * @param records
 */
public record DifyRetrieve(DifyRetrieveQuery query, List<DifyRetrieveItem> records) {
    public record DifyRetrieveQuery(String content) {
    }


    public record DifyRetrieveItem(DifySegment segment, BigDecimal score,
                                   @JsonAlias(value = "tsne_position") Object tsnePosition) {
    }
}