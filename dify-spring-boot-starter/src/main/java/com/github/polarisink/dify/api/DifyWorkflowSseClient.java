package com.github.polarisink.dify.api;

import com.github.polarisink.dify.request.DifyWorkflowRequest;
import com.github.polarisink.dify.response.DifyWorkflowSse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static com.github.polarisink.dify.api.DifyRoutes.RUN_WORKFLOW;

@RequiredArgsConstructor
public class DifyWorkflowSseClient implements DifyWorkflowSseApi{
    private final WebClient webClient;
    @Override
    public Flux<DifyWorkflowSse> runWorkflowSse(DifyWorkflowRequest request) {
        return webClient.post().uri(RUN_WORKFLOW).bodyValue(request).retrieve().bodyToFlux(DifyWorkflowSse.class);
    }
}
