package com.github.polarisink.dify.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.polarisink.dify.DifyConsts.KEY_PREFIX;


/**
 * 基础的difyClient
 */
class BaseDifyClient {
    protected RestClient restClient;
    protected WebClient webClient;

    public BaseDifyClient(String baseUrl, String token, ObjectMapper objectMapper) {
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalArgumentException("dify baseUrl can not be blank");
        }
        if (!baseUrl.startsWith("http://") || !baseUrl.startsWith("https://")) {
            throw new IllegalArgumentException("dify baseUrl is invalid");
        }
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("dify token can not be blank");
        }
        String authorization = token.startsWith(KEY_PREFIX) ? token : KEY_PREFIX + token;
        RestClient.Builder restClientBuilder = RestClient.builder().baseUrl(baseUrl).defaultHeader(HttpHeaders.AUTHORIZATION, authorization);
        WebClient.Builder webClientBuilder = WebClient.builder().baseUrl(baseUrl).defaultHeader(HttpHeaders.AUTHORIZATION, authorization);
        if (objectMapper != null) {
            restClientBuilder.messageConverters(list -> {
                list.removeIf(c -> c.getClass().getName().equals(MappingJackson2HttpMessageConverter.class.getName()));
                MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
                converter.setObjectMapper(objectMapper);
                list.add(0, converter);
            });
        }
        this.restClient = restClientBuilder.build();
        this.webClient = webClientBuilder.build();
    }
}
