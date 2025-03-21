package com.github.polarisink.difytest.api;

import com.github.polarisink.dify.api.DifyChatClient;
import com.github.polarisink.dify.api.DifyWorkflowApi;
import com.github.polarisink.dify.core.DifyProperties;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

import java.io.File;

/**
 * 测试difyChatApi和difyChatClient
 */
@Slf4j
@SpringBootTest
class DifyWorkflowTest {
    @Autowired
    private DifyWorkflowApi difyWorkflowApi;
    @Autowired
    private DifyProperties difyProperties;
    private DifyChatClient client;
    private Resource resource;
    private String user;

    @BeforeEach
    public void setUp() {
        user = "polarisink";
        client = DifyChatClient.builder().baseUrl(difyProperties.getBaseUrl()).token(difyProperties.getChatKey())
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

}