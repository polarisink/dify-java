package com.github.polarisink.dify.api;

import org.junit.jupiter.api.Test;

class DifyChatClientTest {
    @Test
    void build() {
        DifyChatClient build = DifyChatClient.builder().baseUrl("http://").token("sasa").build();
        //DifyChatClient build1 = DifyChatClient.customBuilder().restClient(RestClient.create()).build();

    }

}