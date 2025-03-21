package com.github.polarisink.dify.api;

import com.github.polarisink.dify.request.DifyTextRequest;
import com.github.polarisink.dify.response.DifyTextSse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static com.github.polarisink.dify.api.DifyRoutes.COMPLETION_MESSAGES;

@RequiredArgsConstructor
public class DifyTextSseClient implements DifyTextSseApi {
    private final WebClient webClient;

    @Override
    public Flux<DifyTextSse> chatSse(DifyTextRequest request) {
        Assert.notNull(request, "request can not be null");
        return webClient.post().uri(COMPLETION_MESSAGES).contentType(MediaType.TEXT_EVENT_STREAM).bodyValue(request).retrieve().bodyToFlux(DifyTextSse.class);
    }
}
