package com.github.polarisink.dify.api;

import com.github.polarisink.dify.request.DifyWorkflowRequest;
import com.github.polarisink.dify.request.WorkflowRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;

import static com.github.polarisink.dify.api.DifyRoutes.RUN_WORKFLOW;

/**
 * dify工作流sse-api
 */
public interface DifyWorkflowSseApi {
    /**
     * 运行工作流
     *
     * @param request 请求
     * @return 结果
     */
    @PostExchange(value = RUN_WORKFLOW, contentType = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<WorkflowRequest.WorkflowEvent> runWorkflowSse(@RequestBody DifyWorkflowRequest request);
}