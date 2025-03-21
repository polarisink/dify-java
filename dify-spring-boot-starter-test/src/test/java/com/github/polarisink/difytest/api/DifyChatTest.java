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
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;

import static com.github.polarisink.dify.core.HttpInterfaceUtil.resolveToken;

/**
 * 测试difyChatApi和difyChatClient
 */
@Slf4j
@SpringBootTest
class DifyChatTest {
    @Autowired
    private DifyChatApi difyChatApi;
    @Autowired
    private DifyProperties difyProperties;
    private DifyChatClient difyChatClient;
    private Resource resource;
    private String user;

    @BeforeEach
    public void setUp() {
        user = "polarisink";
        difyChatClient = DifyChatClient.builder().baseUrl(difyProperties.getBaseUrl()).token(difyProperties.getChatKey())
                .interceptor((request, body, execution) -> {
                    long start = System.currentTimeMillis();
                    ClientHttpResponse execute = execution.execute(request, body);
                    log.info("url: {} , timeConsumed:{}", request.getURI(), System.currentTimeMillis() - start);
                    return execute;
                })
                .filter((request, next) -> {
                    long start = System.currentTimeMillis();
                    Mono<ClientResponse> exchange = next.exchange(request);
                    log.info("url: {} ,  timeConsumed: {}", request.url(), System.currentTimeMillis() - start);
                    return exchange;
                })
                .build();
        resource = new FileSystemResource(new File("C:\\Users\\lqsgo\\Desktop\\rocket.py"));
    }


    @Test
    void chat() {
        DifyChatRequest request = DifyChatRequest.builder().user(user).query("介绍一下你自己").build();
        System.out.println(difyChatClient.chat(request));
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
        System.out.println(difyChatApi.conversations(user, null, null, null));
        System.out.println(difyChatClient.conversations(user, null, null, null));
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
        System.out.println(difyChatApi.meta());
        System.out.println(difyChatClient.meta());
    }

    @Test
    void feedback() {
    }

    @Test
    void uploadFile() {
        System.out.println(difyChatApi.uploadFile(resource, user));
        System.out.println(difyChatClient.uploadFile(resource, user));
    }

    @Test
    void info() {
        System.out.println(difyChatApi.info());
        System.out.println(difyChatClient.info());
    }

    @Test
    void parameters() {
        System.out.println(difyChatApi.parameters());
        System.out.println(difyChatClient.parameters());
    }

    @Test
    void textToAudio() {
    }

    @Test
    void chatSse() {
        //todo 后面再编写测试类针对webClient的
        DifyChatRequest request = DifyChatRequest.builder().user(user).query("介绍一下你自己").build();
        difyChatClient.chatSse(request).subscribe(System.out::println);
    }

    @Test
    void customBuilder() {
        RestClient restClient = RestClient.builder().baseUrl(difyProperties.getBaseUrl()).defaultHeader(HttpHeaders.AUTHORIZATION, resolveToken(difyProperties.getChatKey())).build();
        WebClient webClient = WebClient.builder().baseUrl(difyProperties.getBaseUrl()).defaultHeader(HttpHeaders.AUTHORIZATION, resolveToken(difyProperties.getChatKey())).build();
        DifyChatClient customDifyChatCLient = DifyChatClient.customBuilder().restClient(restClient).webClient(webClient).build();
        System.out.println(customDifyChatCLient.meta());
    }

}