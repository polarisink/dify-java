package com.github.polarisink.difytest.api;

import com.github.polarisink.dify.api.DifyTextApi;
import com.github.polarisink.dify.api.DifyTextClient;
import com.github.polarisink.dify.core.DifyProperties;
import com.github.polarisink.dify.enums.DifyResponseModeEnum;
import com.github.polarisink.dify.request.DifyWorkflowRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class DIfyTextTest {
    @Autowired
    DifyTextApi difyTextApi;
    @Autowired
    DifyProperties difyProperties;
    DifyTextClient difyTextClient;

    String user;

    @BeforeEach
    void setUp() {
        difyTextClient = DifyTextClient.builder().baseUrl(difyProperties.getBaseUrl()).token(difyProperties.getTextKey()).build();
        user = "polarisink";
    }

    @Test
    void chat() {
        DifyWorkflowRequest request = DifyWorkflowRequest.builder().responseMode(DifyResponseModeEnum.blocking).user(user).inputs(Map.of("query", "你好")).build();
        System.out.println(difyTextClient.chat(request));
    }
}
