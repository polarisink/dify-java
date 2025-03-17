package com.github.polarisink.difytest.api;

import com.github.polarisink.dify.api.DifyChatClient;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

class DifyChatClientTest {
    @Test
    void build() {
        DifyChatClient build = DifyChatClient.builder().token("aaa").baseUrl("http://baidu.com").build();
        DifyChatClient build1 = DifyChatClient.customBuilder().webClient(WebClient.create()).build();
        System.out.println();

    }

}