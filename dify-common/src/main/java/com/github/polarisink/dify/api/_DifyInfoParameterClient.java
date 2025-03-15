package com.github.polarisink.dify.api;

import com.github.polarisink.dify.response.DifyInfo;
import com.github.polarisink.dify.response.DifyParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
class _DifyInfoParameterClient implements _DifyInfoParameterApi {
    private final RestClient restClient;

    @Override
    public DifyInfo info() {
        return restClient.get().uri(INFO).retrieve().body(DifyInfo.class);
    }

    @Override
    public DifyParameter parameters() {
        return restClient.get().uri(PARAMETERS).retrieve().body(DifyParameter.class);
    }
}
