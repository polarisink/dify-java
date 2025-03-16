package com.github.polarisink.dify.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.polarisink.dify.enums.DifyResponseModeEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 *
 */
@Builder
@ToString
@Getter
public class DifyWorkflowRequest {
    private Map<String, Object> inputs;
    @JsonProperty("response_mode")
    private DifyResponseModeEnum responseMode;
    private String user;

    public static DifyWorkflowRequestBuilder builder() {
        return new DifyWorkflowRequestBuilder() {

            @Override
            public DifyWorkflowRequest build() {
                DifyWorkflowRequest build = super.build();
                if (build.responseMode == null) {
                    throw new IllegalArgumentException("DifyWorkflowRequest#responseMode can not be null");
                }
                return build;
            }
        };
    }

}
