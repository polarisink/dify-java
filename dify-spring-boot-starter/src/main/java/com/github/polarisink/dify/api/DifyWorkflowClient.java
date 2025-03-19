package com.github.polarisink.dify.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.polarisink.dify.request.DifyUserRequest;
import com.github.polarisink.dify.request.DifyWorkflowRequest;
import com.github.polarisink.dify.response.*;
import lombok.Builder;
import org.springframework.core.io.Resource;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

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
        return null;
    }

    @Override
    public DifyWorkflowData workflowInfo(String workflowId) {
        return null;
    }

    @Override
    public DifyResult stopTask(String taskId, DifyUserRequest userRequest) {
        return null;
    }

    @Override
    public DifyPageResponse<DifyWorkflowLog> workflowLogs(String keyword, String status, Integer page, Integer limit) {
        return null;
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
