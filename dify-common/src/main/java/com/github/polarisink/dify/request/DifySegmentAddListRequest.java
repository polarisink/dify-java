package com.github.polarisink.dify.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

/**
 * 分段项目
 */
@Builder
@ToString
@Getter
public class DifySegmentAddListRequest {
    private Collection<DifyBasicSegment> segments;
}
