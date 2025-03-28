package com.github.polarisink.difytest.api;

import com.github.polarisink.dify.api.DifyDatasetApi;
import com.github.polarisink.dify.api.DifyDatasetClient;
import com.github.polarisink.dify.core.DifyProperties;
import com.github.polarisink.dify.core.HttpInterfaceUtil;
import com.github.polarisink.dify.request.DifyDatasetCreateRequest;
import com.github.polarisink.dify.response.DifyDataset;
import com.github.polarisink.dify.response.DifyPageResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;


@Slf4j
@SpringBootTest
class DifyDatasetTest {
    @Autowired
    DifyDatasetApi difyDatasetApi;

    @Autowired
    DifyProperties difyProperties;
    DifyDatasetClient difyDatasetClient;
    DifyDatasetClient customClient;

    String user;

    @BeforeEach
    void setUp() {
        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            log.info("method: {},url: {},body: {}", request.getMethod(), request.getURI(), new String(body));
            return execution.execute(request, body);
        };
        difyDatasetClient = DifyDatasetClient.builder().baseUrl(difyProperties.getBaseUrl()).token(difyProperties.getDatasetKey())
                .interceptor(interceptor)
                .build();
        RestClient restClient = RestClient.builder().baseUrl(difyProperties.getBaseUrl()).requestInterceptor(interceptor).defaultHeader(HttpHeaders.AUTHORIZATION, HttpInterfaceUtil.resolveToken(difyProperties.getDatasetKey())).build();
        customClient = DifyDatasetClient.customBuilder().restClient(restClient).webClient(WebClient.create()).build();
        user = "polarisink";
    }

    @Test
    void createDataset() {
        DifyDatasetCreateRequest build = DifyDatasetCreateRequest.builder().name("ragflow").externalKnowledgeApiId("24f917fe-8182-4b4c-940a-3726240e240d").externalKnowledgeId("c3a0de860a2111f08a1f0242ac1b0006").build();
        difyDatasetClient.createDataset(build);
    }

    @Test
    void datasetPageAndDelete() {
        DifyPageResponse<DifyDataset> page = customClient.datasetPage(1, 20);
        System.out.println(page);
        if (!page.data().isEmpty()) {
            String id = page.data().get(0).id();
            customClient.deleteDataset(id);
            System.out.println(customClient.datasetPage(1, 20));
        }
    }

}
