package com.github.polarisink.dify.api;

import com.github.polarisink.dify.request.DifyChatRequest;
import com.github.polarisink.dify.request.DifyWorkflowRequest;
import com.github.polarisink.dify.request.WorkflowRequest;
import com.github.polarisink.dify.response.ChunkCompletionResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;

import static com.github.polarisink.dify.api.DifyRoutes.COMPLETION_MESSAGES;
import static com.github.polarisink.dify.api.DifyRoutes.RUN_WORKFLOW;

/**
 * dify流api
 */
public interface DifyChatSseApi {

    /**
     * 流式对话
     *
     * @param request 请求
     * @return flux结果
     */
    @PostExchange(value = COMPLETION_MESSAGES, contentType = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<ChunkCompletionResponse> chatSse(@RequestBody DifyChatRequest request);

  }