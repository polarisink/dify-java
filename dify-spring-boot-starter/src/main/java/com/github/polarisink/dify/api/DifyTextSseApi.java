package com.github.polarisink.dify.api;

import com.github.polarisink.dify.request.DifyTextRequest;
import com.github.polarisink.dify.response.DifyTextSse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;

import static com.github.polarisink.dify.api.DifyRoutes.COMPLETION_MESSAGES;

/**
 * dify文本sse-api
 */
public interface DifyTextSseApi {


    /**
     * 发送消息
     * <p>
     * 发送请求给文本生成型应用。
     *
     * @param request 请求
     * @return 结果
     */
    @PostExchange(value = COMPLETION_MESSAGES, contentType = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<DifyTextSse> chatSse(@RequestBody DifyTextRequest request);


}
