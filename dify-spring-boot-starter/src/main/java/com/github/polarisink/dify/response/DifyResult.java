package com.github.polarisink.dify.response;

import java.util.Collection;
import java.util.Objects;

/**
 * 最简单的结果响应，只有result和data字段
 *
 * @param result 结果
 * @param data   数据
 */
public record DifyResult(String result, Collection<String> data) {
    /**
     * 是否成功
     *
     * @return 是否成功
     */
    public boolean isSuccess() {
        return Objects.equals(result, "success");
    }
}
