package com.github.polarisink.dify.api;

import org.junit.jupiter.api.Test;

class DifyChatClientTest {
    @Test
    void build() {
        DifyDatasetClient build = DifyDatasetClient.builder().baseUrl("http://").token("sasa").build();
        System.out.println();
        //DifyChatClient build1 = DifyChatClient.customBuilder().restClient(RestClient.create()).build();

    }

}