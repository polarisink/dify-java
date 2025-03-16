package com.github.polarisink.dify.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * dify的webclient 实现
 */
abstract class AbstractDifyWebClient extends AbstractDifyClient {
    final WebClient webClient;

    public AbstractDifyWebClient(String baseUrl, String token, ObjectMapper objectMapper) {
        super(baseUrl, token);
        WebClient.Builder webClientBuilder = WebClient.builder().baseUrl(baseUrl).defaultHeader(HttpHeaders.AUTHORIZATION, authorization);
        this.webClient = webClientBuilder.build();

    }
}
