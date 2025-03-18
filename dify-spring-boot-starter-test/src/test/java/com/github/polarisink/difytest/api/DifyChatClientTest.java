package com.github.polarisink.difytest.api;

import com.github.polarisink.dify.api.DifyChatApi;
import com.github.polarisink.dify.api.DifyChatClient;
import com.github.polarisink.dify.core.DifyProperties;
import com.github.polarisink.dify.request.DifyChatRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class DifyChatClientTest {
    @Autowired
    private DifyChatApi difyChatApi;
    @Autowired
    private DifyProperties difyProperties;
    private DifyChatClient client;

    @BeforeEach
    public void setUp() {
        client = DifyChatClient.builder().baseUrl(difyProperties.getBaseUrl()).token(difyProperties.getChatKey()).build();
    }


    @Test
    void chat() {
        DifyChatRequest request = DifyChatRequest.builder().user("lqs").query("介绍一下你自己").build();
        System.out.println(client.chat(request));
        System.out.println(difyChatApi.chat(request));
    }

    @Test
    void stopTask() {
//        todo 这里不能单独测试
//        DifyUserRequest request = new DifyUserRequest("lqs");
//        System.out.println(client.stopTask("taskId", request));
//        System.out.println(difyChatApi.stopTask("taskId", request));
    }

    @Test
    void suggestions() {
//        todo 这里不能单独测试
    }

    @Test
    void history() {
    }

    @Test
    void conversations() {
        System.out.println(difyChatApi.conversations("lqs", null, null, null));
        System.out.println(client.conversations("lqs", null, null, null));
    }

    @Test
    void deleteConversation() {
    }

    @Test
    void updateConversionName() {
    }

    @Test
    void audioToText() {
    }

    @Test
    void meta() {
    }

    @Test
    void feedback() {
    }

    @Test
    void uploadFile() {
    }

    @Test
    void info() {
    }

    @Test
    void parameters() {
    }

    @Test
    void textToAudio() {
    }

    @Test
    void chatSse() {
    }

    @Test
    void customBuilder() {
    }

    @Test
    void builder() {
    }
}