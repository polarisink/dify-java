package com.github.polarisink.dify.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;


/**
 * 基础的difyClient
 */
abstract class AbstractDifyRestClient extends AbstractDifyClient {
    protected final RestClient restClient;

    public AbstractDifyRestClient(String baseUrl, String token, ObjectMapper objectMapper) {
        super(baseUrl, token);
        RestClient.Builder restClientBuilder = RestClient.builder().baseUrl(baseUrl).defaultHeader(HttpHeaders.AUTHORIZATION, authorization);
        if (objectMapper != null) {
            restClientBuilder.messageConverters(list -> {
                list.removeIf(c -> c.getClass().getName().equals(MappingJackson2HttpMessageConverter.class.getName()));
                MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
                converter.setObjectMapper(objectMapper);
                list.add(0, converter);
            });
        }
        this.restClient = restClientBuilder.build();
    }
}
