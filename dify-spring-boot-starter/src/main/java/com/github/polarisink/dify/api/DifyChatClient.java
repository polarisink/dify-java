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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.github.polarisink.dify.api.DifyRoutes.*;

@Slf4j
public class DifyChatClient extends AbstractDifyRestClient implements DifyChatApi {

    private final _DifyFileUploadClient _difyFileUploadClient;
    private final _DifyInfoParameterClient _difyInfoParameterClient;
    private final _DifyTextToAudioClient _difyTextToAudioClient;

    @Builder
    public DifyChatClient(String baseUrl, String token, ObjectMapper objectMapper) {
        super(baseUrl, token, objectMapper);
        _difyFileUploadClient = new _DifyFileUploadClient(restClient);
        _difyInfoParameterClient = new _DifyInfoParameterClient(restClient);
        _difyTextToAudioClient = new _DifyTextToAudioClient(restClient);
    }

    @Override
    public DifyChat chat(DifyChatRequest paramMessage) {
        return restClient.post().uri(CHAT_MESSAGES).body(paramMessage).retrieve().body(DifyChat.class);
    }

    @Override
    public DifyResult stopTask(String taskId, DifyUserRequest userRequest) {
        return restClient.post().uri(STOP_CHAT_MESSAGES, taskId).body(userRequest).retrieve().body(DifyResult.class);
    }

    @Override
    public DifyResult suggestions(String messageId, String user) {
        URI uri = UriComponentsBuilder.fromPath(MESSAGES_SUGGESTED).queryParam("user", user).buildAndExpand(messageId).toUri();
        return restClient.post().uri(uri).retrieve().body(DifyResult.class);
    }

    @Override
    public DifyPageResponse<DifyMessage> history(String user, String conversionId, String firstId, int limit) {
        URI uri = UriComponentsBuilder.fromPath(MESSAGES).queryParam("user", user).queryParam("conversion_id", conversionId).queryParam("first_id", firstId).queryParam("limit", limit).build().toUri();
        return restClient.get().uri(uri).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }

    @Override
    public DifyPageResponse<DifyConversion> conversions(String user, String lastId, int limit, String sortBy) {
        URI uri = UriComponentsBuilder.fromPath(CONVERSIONS).queryParam("user", user).queryParam("last_id", lastId).queryParam("sort_by", sortBy).queryParam("limit", limit).build().toUri();
        return restClient.get().uri(uri).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }

    @Override
    public DifyResult deleteConversation(String conversationId, DifyUserRequest userRequest) {
        return restClient.method(HttpMethod.DELETE).uri(CONVERSION_BY_ID, conversationId).body(userRequest).retrieve().body(DifyResult.class);
    }

    @Override
    public DifyConversion updateConversionName(String conversationId, DifyConversionRequest request) {
        return restClient.post().uri(CONVERSION_NAME, conversationId).body(request).retrieve().body(DifyConversion.class);
    }

    @Override
    public DifyAudioToText audioToText(Resource file, String user) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>() {{
            add("file", file);
            add("user", user);
        }};
        return restClient.post().uri(AUDIO_TO_TEXT).contentType(MediaType.MULTIPART_FORM_DATA).body(map).retrieve().body(DifyAudioToText.class);
    }

    @Override
    public DifyMeta meta() {
        return restClient.post().uri(META).retrieve().body(DifyMeta.class);
    }

    @Override
    public DifyResult feedback(String messageId, DifyFeedbackRequest request) {
        return null;
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
