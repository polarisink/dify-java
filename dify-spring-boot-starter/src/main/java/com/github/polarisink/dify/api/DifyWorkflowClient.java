package com.github.polarisink.dify.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.polarisink.dify.request.DifyUserRequest;
import com.github.polarisink.dify.request.DifyWorkflowRequest;
import com.github.polarisink.dify.response.*;
import lombok.Builder;
import org.springframework.core.io.Resource;

public class DifyWorkflowClient extends AbstractDifyRestClient implements DifyWorkflowApi {

    private final _DifyFileUploadClient _difyFileUploadClient;
    private final _DifyInfoParameterClient _difyInfoParameterClient;

    @Builder
    public DifyWorkflowClient(String baseUrl, String token, ObjectMapper objectMapper) {
        super(baseUrl, token, objectMapper);
        _difyFileUploadClient = new _DifyFileUploadClient(restClient);
        _difyInfoParameterClient = new _DifyInfoParameterClient(restClient);
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
}
