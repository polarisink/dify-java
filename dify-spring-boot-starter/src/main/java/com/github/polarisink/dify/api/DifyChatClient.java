package com.github.polarisink.dify.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.polarisink.dify.request.DifyChatRequest;
import com.github.polarisink.dify.request.DifyConversionRequest;
import com.github.polarisink.dify.request.DifyFeedbackRequest;
import com.github.polarisink.dify.request.DifyUserRequest;
import com.github.polarisink.dify.response.*;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.util.Optional;

import static com.github.polarisink.dify.api.DifyRoutes.*;

/**
 * 聊天客户端
 */
@Slf4j
public class DifyChatClient extends AbstractDifyClient implements DifyChatApi, DifyChatSseApi {

    private final _DifyFileUploadClient _difyFileUploadClient;
    private final _DifyInfoParameterClient _difyInfoParameterClient;
    private final _DifyTextToAudioClient _difyTextToAudioClient;
    private final _DifyFeedbackClient _difyFeedbackClient;
    private final DifyChatSseClient difyChatSseClient;

    @Builder(builderClassName = "DifyChatClientCustomBuilder", builderMethodName = "customBuilder")
    public DifyChatClient(RestClient restClient, WebClient webClient) {
        super(restClient, webClient);
        _difyFileUploadClient = new _DifyFileUploadClient(restClient);
        _difyInfoParameterClient = new _DifyInfoParameterClient(restClient);
        _difyTextToAudioClient = new _DifyTextToAudioClient(restClient);
        _difyFeedbackClient = new _DifyFeedbackClient(restClient);
        difyChatSseClient = new DifyChatSseClient(webClient);
    }

    @Builder(builderClassName = "DifyChatClientBuilder")
    public DifyChatClient(String baseUrl, String token, ObjectMapper objectMapper, ClientHttpRequestInterceptor interceptor, ExchangeFilterFunction filter) {
        super(baseUrl, token, objectMapper, interceptor, filter);
        _difyFileUploadClient = new _DifyFileUploadClient(restClient);
        _difyInfoParameterClient = new _DifyInfoParameterClient(restClient);
        _difyTextToAudioClient = new _DifyTextToAudioClient(restClient);
        _difyFeedbackClient = new _DifyFeedbackClient(restClient);
        difyChatSseClient = new DifyChatSseClient(webClient);
    }

    @Override
    public DifyChat chat(DifyChatRequest chatRequest) {
        Assert.notNull(chatRequest, "chatRequest can not be null");
        return restClient.post().uri(CHAT_MESSAGES).body(chatRequest).retrieve().body(DifyChat.class);
    }

    @Override
    public DifyResult stopTask(String taskId, DifyUserRequest userRequest) {
        Assert.hasText(taskId, "taskId can not be blank");
        Assert.notNull(userRequest, "userRequest can not be null");
        return restClient.post().uri(STOP_CHAT_MESSAGES, taskId).body(userRequest).retrieve().body(DifyResult.class);
    }

    @Override
    public DifyResult suggestions(String messageId, String user) {
        Assert.hasText(messageId, "messageId can not be blank");
        Assert.hasText(user, "user can not be blank");
        String uri = UriComponentsBuilder.fromUriString(MESSAGES_SUGGESTED).queryParam("user", user).buildAndExpand(messageId).toUriString();
        return restClient.post().uri(uri).retrieve().body(DifyResult.class);
    }

    @Override
    public DifyPageResponse<DifyMessage> history(String user, String conversionId, String firstId, Integer limit) {
        Assert.hasText(user, "user can not be blank");
        String uri = UriComponentsBuilder.fromUriString(MESSAGES).queryParam("user", user).queryParam("conversion_id", conversionId).queryParam("first_id", firstId).queryParam("limit", limit).build().toUriString();
        return restClient.get().uri(uri).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }

    @Override
    public DifyPageResponse<DifyConversion> conversations(String user, String lastId, Integer limit, String sortBy) {
        Assert.hasText(user, "user can not be blank");
        String uri = UriComponentsBuilder.fromUriString(CONVERSATIONS).queryParam("user", user).queryParamIfPresent("last_id", Optional.ofNullable(lastId)).queryParamIfPresent("sort_by", Optional.ofNullable(sortBy)).queryParamIfPresent("limit", Optional.ofNullable(limit)).build().toUriString();
        return restClient.get().uri(uri).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }

    @Override
    public DifyResult deleteConversation(String conversationId, DifyUserRequest userRequest) {
        Assert.hasText(conversationId, "conversationId can not be blank");
        Assert.notNull(userRequest, "userRequest can not be null");
        return restClient.method(HttpMethod.DELETE).uri(CONVERSION_BY_ID, conversationId).body(userRequest).retrieve().body(DifyResult.class);
    }

    @Override
    public DifyConversion updateConversionName(String conversationId, DifyConversionRequest request) {
        Assert.hasText(conversationId, "conversationId can not be blank");
        Assert.notNull(request, "request can not be null");
        return restClient.post().uri(CONVERSION_NAME, conversationId).body(request).retrieve().body(DifyConversion.class);
    }

    @Override
    public DifyAudioToText audioToText(Resource file, String user) {
        Assert.notNull(file, "file can not be null");
        Assert.hasText(user, "user can not be blank");
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>() {{
            add("file", file);
            add("user", user);
        }};
        return restClient.post().uri(AUDIO_TO_TEXT).contentType(MediaType.MULTIPART_FORM_DATA).body(map).retrieve().body(DifyAudioToText.class);
    }

    @Override
    public DifyMeta meta() {
        return restClient.get().uri(META).retrieve().body(DifyMeta.class);
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
    public Flux<DifyChatSse> chatSse(DifyChatRequest request) {
        return difyChatSseClient.chatSse(request);
    }
}
