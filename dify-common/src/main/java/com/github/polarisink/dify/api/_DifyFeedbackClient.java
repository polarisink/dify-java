package com.github.polarisink.dify.api;

import com.github.polarisink.dify.request.DifyFeedbackRequest;
import com.github.polarisink.dify.response.DifyResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
class _DifyFeedbackClient implements _DifyFeedbackApi {
    private final RestClient restClient;

    @Override
    public DifyResult feedback(String messageId, DifyFeedbackRequest request) {
        return restClient.post().uri(MESSAGE_FEEDBACK, messageId).body(request).retrieve().body(DifyResult.class);
    }
}
