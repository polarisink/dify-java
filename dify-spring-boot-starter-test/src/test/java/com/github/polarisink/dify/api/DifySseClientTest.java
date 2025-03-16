package com.github.polarisink.dify.api;

import org.junit.jupiter.api.Test;

public class DifySseClientTest {

    @Test
    void build() {
        DifySseClient build = DifySseClient.builder().baseUrl("http://baidu.com").token("dsdaada").objectMapper(null).build();
        System.out.println(build);
    }

}
