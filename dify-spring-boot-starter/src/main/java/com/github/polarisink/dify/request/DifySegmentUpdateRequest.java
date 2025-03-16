package com.github.polarisink.dify.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * 分段更新请求
 */
@ToString(callSuper = true)
@Getter
@SuperBuilder
public class DifySegmentUpdateRequest extends DifyBasicSegment {
    /**
     * 非必填
     */
    private Boolean enabled;
    /**
     * 是否重新生成子分段，非必填
     */
    @JsonProperty("regenerate_child_chunks")
    private Boolean regenerateChildChunks;

}
