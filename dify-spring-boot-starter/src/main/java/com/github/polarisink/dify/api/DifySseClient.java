package com.github.polarisink.dify.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.polarisink.dify.request.DifyChatRequest;
import com.github.polarisink.dify.request.DifyWorkflowRequest;
import com.github.polarisink.dify.request.WorkflowRequest;
import com.github.polarisink.dify.response.ChunkCompletionResponse;
import lombok.Builder;
import reactor.core.publisher.Flux;

import static com.github.polarisink.dify.api.DifyRoutes.COMPLETION_MESSAGES;
import static com.github.polarisink.dify.api.DifyRoutes.RUN_WORKFLOW;

public class DifySseClient extends AbstractDifyWebClient implements DifySseApi {
    @Builder
    public DifySseClient(String baseUrl, String token, ObjectMapper objectMapper) {
        super(baseUrl, token, objectMapper);
    }

    @Override
    public Flux<ChunkCompletionResponse> chat(DifyChatRequest request) {
        return webClient.post().uri(COMPLETION_MESSAGES).bodyValue(request).retrieve().bodyToFlux(ChunkCompletionResponse.class);
    }

    @Override
    public Flux<WorkflowRequest.WorkflowEvent> runWorkflow(DifyWorkflowRequest request) {
        return webClient.post().uri(RUN_WORKFLOW).bodyValue(request).retrieve().bodyToFlux(WorkflowRequest.WorkflowEvent.class);
    }
}
