package com.github.polarisink.dify.api;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClient;

/**
 * 文本转语音客户端
 */
@RequiredArgsConstructor
public class _DifyTextToAudioClient implements _DifyTextToAudioApi {
    private final RestClient restClient;

    @Override
    public Resource textToAudio(String messageId, String text, String user) {
        Assert.hasText(messageId, "messageId can not be blank");
        Assert.hasText(text, "text can not be blank");
        Assert.hasText(user, "user can not be blank");
        return restClient.post().accept(MediaType.parseMediaType("audio/wav")).retrieve().body(Resource.class);
    }
}
