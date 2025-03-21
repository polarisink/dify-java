package com.github.polarisink.dify.api;

import com.github.polarisink.dify.request.DifyChatRequest;
import com.github.polarisink.dify.response.DifyChatSse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;

import static com.github.polarisink.dify.api.DifyRoutes.CHAT_MESSAGES;

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
    @PostExchange(value = CHAT_MESSAGES, contentType = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<DifyChatSse> chatSse(@RequestBody DifyChatRequest request);

}