package com.github.polarisink.dify.api;

import com.github.polarisink.dify.request.DifyFeedbackRequest;
import com.github.polarisink.dify.response.DifyResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

import static com.github.polarisink.dify.api.DifyRoutes.MESSAGE_FEEDBACK;

/**
 * 公共反馈api
 */
interface _DifyFeedbackApi {


    /**
     * 消息反馈（点赞）
     * <p>
     * 消息终端用户反馈、点赞，方便应用开发者优化输出预期。
     *
     * @param messageId 信息id
     * @param request   请求
     * @return 结果
     */
    @PostExchange(MESSAGE_FEEDBACK)
    DifyResult feedback(@PathVariable String messageId, @RequestBody DifyFeedbackRequest request);

}
