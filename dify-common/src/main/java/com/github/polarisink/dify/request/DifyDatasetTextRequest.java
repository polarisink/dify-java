package com.github.polarisink.dify.request;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 通过文本创建或更新文档请求
 */
@ToString(callSuper = true)
@Getter
@SuperBuilder
public final class DifyDatasetTextRequest extends DifyDatasetBasicRequest {
    /**
     * 文档名称
     */
    private String name;
    /**
     * 文档内容
     */
    private String text;
}
