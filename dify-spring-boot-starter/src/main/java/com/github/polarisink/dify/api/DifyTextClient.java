package com.github.polarisink.dify.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.polarisink.dify.request.DifyFeedbackRequest;
import com.github.polarisink.dify.request.DifyUserRequest;
import com.github.polarisink.dify.request.DifyWorkflowRequest;
import com.github.polarisink.dify.response.*;
import lombok.Builder;
import org.springframework.core.io.Resource;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * dify文本客户端
 */
public class DifyTextClient extends AbstractDifyClient implements DifyTextApi {

    private final _DifyFileUploadClient _difyFileUploadClient;
    private final _DifyInfoParameterClient _difyInfoParameterClient;
    private final _DifyTextToAudioClient _difyTextToAudioClient;
    private final _DifyFeedbackClient _difyFeedbackClient;

    @Builder(builderClassName = "DifyTextClientCustomBuilder", builderMethodName = "customBuilder")
    public DifyTextClient(RestClient restClient, WebClient webClient) {
        super(restClient, webClient);
        _difyFileUploadClient = new _DifyFileUploadClient(restClient);
        _difyInfoParameterClient = new _DifyInfoParameterClient(restClient);
        _difyTextToAudioClient = new _DifyTextToAudioClient(restClient);
        _difyFeedbackClient = new _DifyFeedbackClient(restClient);
    }

    @Builder(builderClassName = "DifyTextClientBuilder")
    public DifyTextClient(String baseUrl, String token, ObjectMapper objectMapper, ClientHttpRequestInterceptor interceptor, ExchangeFilterFunction filter) {
        super(baseUrl, token, objectMapper, interceptor, filter);
        _difyFileUploadClient = new _DifyFileUploadClient(restClient);
        _difyInfoParameterClient = new _DifyInfoParameterClient(restClient);
        _difyTextToAudioClient = new _DifyTextToAudioClient(restClient);
        _difyFeedbackClient = new _DifyFeedbackClient(restClient);
    }

    @Override
    public DifyTextChat chat(DifyWorkflowRequest request) {
        return null;
    }

    @Override
    public DifyResult stopTask(String taskId, DifyUserRequest request) {
        return null;
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
}
