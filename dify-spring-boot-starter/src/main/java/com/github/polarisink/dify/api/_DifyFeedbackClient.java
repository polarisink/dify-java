package com.github.polarisink.dify.api;

import com.github.polarisink.dify.request.DifyFeedbackRequest;
import com.github.polarisink.dify.response.DifyResult;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClient;

import static com.github.polarisink.dify.api.DifyRoutes.MESSAGE_FEEDBACK;

/**
 * 反馈接口实现
 */
@RequiredArgsConstructor
class _DifyFeedbackClient implements _DifyFeedbackApi {
    private final RestClient restClient;

    @Override
    public DifyResult feedback(String messageId, DifyFeedbackRequest request) {
        Assert.hasText(messageId, "messageId can not be blank");
        Assert.notNull(request, "request can not be null");
        return restClient.post().uri(MESSAGE_FEEDBACK, messageId).body(request).retrieve().body(DifyResult.class);
    }
}
