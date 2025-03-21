package com.github.polarisink.dify.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.polarisink.dify.request.DifyFeedbackRequest;
import com.github.polarisink.dify.request.DifyTextRequest;
import com.github.polarisink.dify.request.DifyUserRequest;
import com.github.polarisink.dify.request.DifyWorkflowRequest;
import com.github.polarisink.dify.response.*;
import lombok.Builder;
import org.springframework.core.io.Resource;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static com.github.polarisink.dify.api.DifyRoutes.COMPLETION_MESSAGES;
import static com.github.polarisink.dify.api.DifyRoutes.STOP_MESSAGES;

/**
 * dify文本客户端
 */
public class DifyTextClient extends AbstractDifyClient implements DifyTextApi, DifyTextSseApi {

    private final _DifyFileUploadClient _difyFileUploadClient;
    private final _DifyInfoParameterClient _difyInfoParameterClient;
    private final _DifyTextToAudioClient _difyTextToAudioClient;
    private final _DifyFeedbackClient _difyFeedbackClient;
    private final DifyTextSseClient difyTextSseClient;

    @Builder(builderClassName = "DifyTextClientCustomBuilder", builderMethodName = "customBuilder")
    public DifyTextClient(RestClient restClient, WebClient webClient) {
        super(restClient, webClient);
        _difyFileUploadClient = new _DifyFileUploadClient(restClient);
        _difyInfoParameterClient = new _DifyInfoParameterClient(restClient);
        _difyTextToAudioClient = new _DifyTextToAudioClient(restClient);
        _difyFeedbackClient = new _DifyFeedbackClient(restClient);
        difyTextSseClient = new DifyTextSseClient(webClient);
    }

    @Builder(builderClassName = "DifyTextClientBuilder")
    public DifyTextClient(String baseUrl, String token, ObjectMapper objectMapper, ClientHttpRequestInterceptor interceptor, ExchangeFilterFunction filter) {
        super(baseUrl, token, objectMapper, interceptor, filter);
        _difyFileUploadClient = new _DifyFileUploadClient(restClient);
        _difyInfoParameterClient = new _DifyInfoParameterClient(restClient);
        _difyTextToAudioClient = new _DifyTextToAudioClient(restClient);
        _difyFeedbackClient = new _DifyFeedbackClient(restClient);
        difyTextSseClient = new DifyTextSseClient(webClient);
    }

    @Override
    public DifyTextChat chat(DifyWorkflowRequest request) {
        Assert.notNull(request, "request can not be null");
        return restClient.post().uri(COMPLETION_MESSAGES).body(request).retrieve().body(DifyTextChat.class);
    }

    @Override
    public DifyResult stopTask(String taskId, DifyUserRequest request) {
        Assert.hasText(taskId, "taskId can not be blank");
        Assert.notNull(request, "request can not be null");
        return restClient.post().uri(STOP_MESSAGES, taskId).body(request).retrieve().body(DifyResult.class);
    }

    @Override
    public DifyResult feedback(String messageId, DifyFeedbackRequest request) {
        return _difyFeedbackClient.feedback(messageId, request);
    }

    @Override
    public DifyFile uploadFile(Resource file, String user) {
        return _difyFileUploadClient.uploadFile(file, user);
    }

    @Override
    public DifyInfo info() {
        return _difyInfoParameterClient.info();
    }

    @Override
    public DifyParameter parameters() {
        return _difyInfoParameterClient.parameters();
    }

    @Override
    public Resource textToAudio(String messageId, String text, String user) {
        return _difyTextToAudioClient.textToAudio(messageId, text, user);
    }

    @Override
    public Flux<DifyTextSse> chatSse(DifyTextRequest request) {
        return difyTextSseClient.chatSse(request);
    }
}
