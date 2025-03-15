package com.github.polarisink.dify.api;

import com.github.polarisink.dify.DifyPageResponse;
import com.github.polarisink.dify.request.DifyUserRequest;
import com.github.polarisink.dify.request.DifyWorkflowRequest;
import com.github.polarisink.dify.response.DifyResult;
import com.github.polarisink.dify.response.DifyWorkflow;
import com.github.polarisink.dify.response.DifyWorkflowData;
import com.github.polarisink.dify.response.DifyWorkflowLog;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * dify工作流api
 */
public interface DifyWorkflowApi extends _DifyFileUploadApi, _DifyInfoParameterApi {

    String RUN_WORKFLOW = "/workflows/run";
    String WORKFLOW_INFO = "/workflows/run/{workflowId}";

    String STOP_WORKFLOW = "/workflows/run/{workflowId}";

    String WORKFLOW_LOGS = "/workflows/logs";

    /**
     * 执行 workflow
     * <p>
     * 执行 workflow，没有已发布的 workflow，不可执行。
     *
     * @param request 请求
     * @return 结果
     */
    @PostExchange(RUN_WORKFLOW)
    DifyWorkflow runWorkflow(@RequestBody DifyWorkflowRequest request);

    /**
     * 获取workflow执行情况
     *
     * @param workflowId workflow 执行 ID，可在流式返回 Chunk 中获取
     * @return 结果
     */
    @GetExchange(WORKFLOW_INFO)
    DifyWorkflowData workflowInfo(@PathVariable String workflowId);

    /**
     * 停止响应
     * <p>
     * 仅支持流式模式
     *
     * @param taskId      任务id
     * @param userRequest 请求
     * @return 是否成功
     */
    @PostExchange(STOP_WORKFLOW)
    DifyResult stopTask(@PathVariable String taskId, @RequestBody DifyUserRequest userRequest);

    /**
     * 获取 workflow 日志
     * <p>
     * 倒序返回workflow日志
     *
     * @param keyword 关键字
     * @param status  执行状态 succeeded/failed/stopped
     * @param page    当前页码, 默认1.
     * @param limit   每页条数, 默认20.
     * @return 分页结果
     */
    @GetExchange(WORKFLOW_LOGS)
    DifyPageResponse<DifyWorkflowLog> workflowLogs(@RequestParam(name = "keyword", required = false) String keyword, @RequestParam(name = "status", required = false) String status, @RequestParam(name = "page", required = false, defaultValue = "1") Integer page, @RequestParam(name = "limit", defaultValue = "20", required = false) Integer limit);

}
