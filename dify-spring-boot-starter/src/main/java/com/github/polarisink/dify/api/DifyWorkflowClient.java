package com.github.polarisink.dify.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.polarisink.dify.request.DifyUserRequest;
import com.github.polarisink.dify.request.DifyWorkflowRequest;
import com.github.polarisink.dify.response.*;
import lombok.Builder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.util.Optional;

import static com.github.polarisink.dify.api.DifyRoutes.*;

/**
 * dify工作流客户端，支持全部功能
 */
public class DifyWorkflowClient extends AbstractDifyClient implements DifyWorkflowApi, DifyWorkflowSseApi {

    private final _DifyFileUploadClient _difyFileUploadClient;
    private final _DifyInfoParameterClient _difyInfoParameterClient;
    private final DifyWorkflowSseClient _difyWorkflowSseClient;

    @Builder(builderClassName = "DifyWorkflowClientCustomBuilder", builderMethodName = "customBuilder")
    public DifyWorkflowClient(RestClient restClient, WebClient webClient) {
        super(restClient, webClient);
        _difyFileUploadClient = new _DifyFileUploadClient(restClient);
        _difyInfoParameterClient = new _DifyInfoParameterClient(restClient);
        _difyWorkflowSseClient = new DifyWorkflowSseClient(webClient);
    }

    @Builder(builderClassName = "DifyWorkflowClientBuilder")
    public DifyWorkflowClient(String baseUrl, String token, ObjectMapper objectMapper, ClientHttpRequestInterceptor interceptor, ExchangeFilterFunction filter) {
        super(baseUrl, token, objectMapper, interceptor, filter);
        _difyFileUploadClient = new _DifyFileUploadClient(restClient);
        _difyInfoParameterClient = new _DifyInfoParameterClient(restClient);
        _difyWorkflowSseClient = new DifyWorkflowSseClient(webClient);
    }

    @Override
    public DifyWorkflow runWorkflow(DifyWorkflowRequest request) {
        Assert.notNull(request, "request can not be null");
        return restClient.post().uri(RUN_WORKFLOW).body(request).retrieve().body(DifyWorkflow.class);
    }

    @Override
    public DifyWorkflowData workflowInfo(String workflowId) {
        Assert.hasText(workflowId, "workflowId can not be blank");
        return restClient.get().uri(WORKFLOW_INFO, workflowId).retrieve().body(DifyWorkflowData.class);
    }

    @Override
    public DifyResult stopTask(String taskId, DifyUserRequest userRequest) {
        Assert.hasText(taskId, "taskId can not be blank");
        Assert.notNull(userRequest, "userRequest can not be null");
        return restClient.post().uri(STOP_WORKFLOW, taskId).body(userRequest).retrieve().body(DifyResult.class);
    }

    @Override
    public DifyPageResponse<DifyWorkflowLog> workflowLogs(String keyword, String status, Integer page, Integer limit) {
        String uri = UriComponentsBuilder.fromUriString(WORKFLOW_LOGS)
                .queryParamIfPresent("keyword", Optional.ofNullable(keyword))
                .queryParamIfPresent("status", Optional.ofNullable(status))
                .queryParam("page", Optional.ofNullable(page).orElse(1))
                .queryParam("limit", Optional.ofNullable(limit).orElse(20))
                .build().toUri().toString();
        return restClient.get().uri(uri).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }

    @Override
    public DifyFile uploadFile(Resource file, String user) {
        return _difyFileUploadClient.uploadFile(file, user);
    }

    @Override
    public DifyInfo info() {
        return _difyInfoParameterClient.info();
    }

    @Override
    public DifyParameter parameters() {
        return _difyInfoParameterClient.parameters();
    }

    @Override
    public Flux<DifyWorkflowSse> runWorkflowSse(DifyWorkflowRequest request) {
        return _difyWorkflowSseClient.runWorkflowSse(request);
    }
}
