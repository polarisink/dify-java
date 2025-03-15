package com.github.polarisink.dify.api;

import com.github.polarisink.dify.request.DifyUserRequest;
import com.github.polarisink.dify.request.DifyWorkflowRequest;
import com.github.polarisink.dify.response.DifyResult;
import com.github.polarisink.dify.response.DifyTextChat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

/**
 * dify文本api
 */
public interface DifyTextApi extends _DifyFileUploadApi, _DifyInfoParameterApi, _DifyTextToAudioApi, _DifyFeedbackApi {

    String COMPLETION_MESSAGES = "/completion-messages";

    String STOP_MESSAGES = "/completion-messages/{taskId}/stop";

    /**
     * 发送消息
     * <p>
     * 发送请求给文本生成型应用。
     *
     * @param request 请求
     * @return 结果
     */
    @PostExchange(COMPLETION_MESSAGES)
    DifyTextChat chat(@RequestBody DifyWorkflowRequest request);

    /**
     * 停止响应
     * <p>
     * 仅支持流式模式。
     *
     * @param taskId  任务id
     * @param request 用户请求
     * @return 是否成功
     */
    @PostExchange(STOP_MESSAGES)
    DifyResult stopTask(@PathVariable String taskId, @RequestBody DifyUserRequest request);


}
