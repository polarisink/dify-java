package com.github.polarisink.dify.api;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import static com.github.polarisink.dify.DifyConsts.AUDIO_WAV;

@RequiredArgsConstructor
public class _DifyTextToAudioClient implements _DifyTextToAudioApi {
    private final RestClient restClient;

    @Override
    public Resource textToAudio(String messageId, String text, String user) {
        return restClient.post().accept(MediaType.parseMediaType(AUDIO_WAV)).retrieve().body(Resource.class);
    }
}
