package com.github.polarisink.dify.api;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class _DifyTextToAudioClient implements _DifyTextToAudioApi {
    private final RestClient restClient;

    @Override
    public Resource textToAudio(String messageId, String text, String user) {
        return restClient.post().accept(MediaType.parseMediaType("audio/wav")).retrieve().body(Resource.class);
    }
}
