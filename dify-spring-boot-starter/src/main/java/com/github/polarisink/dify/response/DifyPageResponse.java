package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

/**
 * dify分页请求包装
 *
 * @param data    数据
 * @param hasMore 是否有更多
 * @param limit   页码大小
 * @param total   总数
 * @param page    页码
 * @param <T>
 */
public record DifyPageResponse<T>(List<T> data, @JsonAlias("has_more") Boolean hasMore, Integer limit, Integer total,
                                  Integer page) {
}
