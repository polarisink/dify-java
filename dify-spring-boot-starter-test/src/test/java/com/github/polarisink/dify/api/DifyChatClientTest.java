package com.github.polarisink.dify.api;

import org.junit.jupiter.api.Test;

class DifyChatClientTest {
    @Test
    void build() {
        DifyChatClient build = DifyChatClient.builder().baseUrl("http://").token("sasa").build();

    }

}