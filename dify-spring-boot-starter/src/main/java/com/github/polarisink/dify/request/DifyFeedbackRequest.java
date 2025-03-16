package com.github.polarisink.dify.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 反馈请求
 */
@ToString
@Getter
@Builder
public final class DifyFeedbackRequest {
    private String rating;
    private String user;
    private String content;
}
