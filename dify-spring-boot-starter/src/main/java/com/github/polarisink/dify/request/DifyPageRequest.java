package com.github.polarisink.dify.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * dify分页请求
 */
@ToString
@Getter
@Builder
public class DifyPageRequest {
    /**
     * 页码
     */
    private Integer page;
    /**
     * 返回条数，可选，默认 20，范围 1-100
     */
    @Builder.Default
    private Integer limit = 20;
    /**
     * 关键词
     */
    private String keyword;
}
