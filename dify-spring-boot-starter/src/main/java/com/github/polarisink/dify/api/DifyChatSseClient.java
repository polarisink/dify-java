package com.github.polarisink.dify.api;

import com.github.polarisink.dify.request.DifyChatRequest;
import com.github.polarisink.dify.response.DifyChatSse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static com.github.polarisink.dify.api.DifyRoutes.CHAT_MESSAGES;

@RequiredArgsConstructor
public class DifyChatSseClient implements DifyChatSseApi {
    private final WebClient webClient;

    @Override
    public Flux<DifyChatSse> chatSse(DifyChatRequest request) {
        Assert.notNull(request, "request can not be null");
        Assert.notNull(webClient, "webClient can not be null");
        return webClient.post().uri(CHAT_MESSAGES).contentType(MediaType.TEXT_EVENT_STREAM).bodyValue(request).retrieve().bodyToFlux(DifyChatSse.class);
    }
}
