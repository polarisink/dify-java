package com.github.polarisink.difytest.api;

import com.github.polarisink.dify.api.DifyDatasetApi;
import com.github.polarisink.dify.api.DifyDatasetClient;
import com.github.polarisink.dify.core.DifyProperties;
import com.github.polarisink.dify.core.HttpInterfaceUtil;
import com.github.polarisink.dify.enums.DifyDocFormEnum;
import com.github.polarisink.dify.enums.DifyIndexTechniqueEnum;
import com.github.polarisink.dify.request.DifyDatasetCreateRequest;
import com.github.polarisink.dify.request.DifyDatasetTextRequest;
import com.github.polarisink.dify.response.DifyDataset;
import com.github.polarisink.dify.response.DifyDocumentWrapper;
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
    void createDocByText() {
        DifyDatasetCreateRequest build = DifyDatasetCreateRequest.builder().name("3").build();
        DifyDataset dataset = difyDatasetClient.createDataset(build);
        DifyDatasetTextRequest textRequest = DifyDatasetTextRequest.builder().text("你干嘛哎咳哟").docForm(DifyDocFormEnum.text_model).indexingTechnique(DifyIndexTechniqueEnum.economy).docLanguage("English").name(user).build();
        try {
            DifyDocumentWrapper docByText = difyDatasetClient.createDocByText("22f5b037-0aeb-4e85-b9ff-4ffc5eaa8827", textRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
