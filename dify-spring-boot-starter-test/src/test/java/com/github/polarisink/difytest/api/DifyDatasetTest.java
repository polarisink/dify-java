package com.github.polarisink.difytest.api;

import com.github.polarisink.dify.api.DifyDatasetApi;
import com.github.polarisink.dify.api.DifyDatasetClient;
import com.github.polarisink.dify.core.DifyProperties;
import com.github.polarisink.dify.enums.DifyDocFormEnum;
import com.github.polarisink.dify.enums.DifyIndexTechniqueEnum;
import com.github.polarisink.dify.request.DifyDatasetTextRequest;
import com.github.polarisink.dify.response.DifyDocumentWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class DifyDatasetTest {
    @Autowired
    DifyDatasetApi difyDatasetApi;

    @Autowired
    DifyProperties difyProperties;
    DifyDatasetClient difyDatasetClient;

    String user;

    @BeforeEach
    void setUp() {
        difyDatasetClient = DifyDatasetClient.builder().baseUrl(difyProperties.getBaseUrl()).token(difyProperties.getDatasetKey())
                .interceptor((request, body, execution) -> {
                    log.info("method: {},url: {},body: {}", request.getMethod(), request.getURI(), new String(body));
                    return execution.execute(request, body);
                })
                .build();
        user = "polarisink";
    }

    @Test
    void createDocByText() {
        DifyDatasetTextRequest textRequest = DifyDatasetTextRequest.builder().text("你干嘛哎咳哟").docForm(DifyDocFormEnum.text_model).indexingTechnique(DifyIndexTechniqueEnum.economy).docLanguage("English").name(user).build();
        try {
            DifyDocumentWrapper docByText = difyDatasetClient.createDocByText("22f5b037-0aeb-4e85-b9ff-4ffc5eaa8827", textRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
