package com.github.polarisink.dify.api;

import com.github.polarisink.dify.response.DifyPageResponse;
import com.github.polarisink.dify.request.DifyChatRequest;
import com.github.polarisink.dify.request.DifyConversionRequest;
import com.github.polarisink.dify.request.DifyUserRequest;
import com.github.polarisink.dify.response.*;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import static com.github.polarisink.dify.api.DifyRoutes.*;

/**
 * dify聊天室接口
 */
public interface DifyChatApi extends _DifyFileUploadApi, _DifyInfoParameterApi, _DifyTextToAudioApi, _DifyFeedbackApi {
    /**
     * 发送对话消息
     * <p>
     * 是同步调用的，立刻得到结果
     *
     * @param paramMessage message
     * @return 结果
     */
    @PostExchange(CHAT_MESSAGES)
    DifyChat chat(@RequestBody DifyChatRequest paramMessage);

    /**
     * 停止任务，仅支持流模式
     *
     * @param taskId      任务id
     * @param userRequest 用户
     */
    @PostExchange(STOP_CHAT_MESSAGES)
    DifyResult stopTask(@PathVariable String taskId, @RequestBody DifyUserRequest userRequest);

    /**
     * 获取下一轮建议问题列表
     *
     * @param messageId 消息id
     * @param user      用户标识，由开发者定义规则，需保证用户标识在应用内唯一。
     */
    @GetExchange(MESSAGES_SUGGESTED)
    DifyResult suggestions(@PathVariable String messageId, @RequestParam("user") String user);


    /**
     * 获取会话历史消息
     * <p>
     * 滚动加载形式返回历史聊天记录，第一页返回最新 limit 条，即：倒序返回。
     *
     * @param user         用户标识，由开发者定义规则，需保证用户标识在应用内唯一
     * @param conversionId 会话id
     * @param firstId      当前页第一条聊天记录的 ID，默认 null
     * @param limit        一次请求返回多少条聊天记录，默认 20 条。
     */
    @GetExchange(MESSAGES)
    DifyPageResponse<DifyMessage> history(@RequestParam String user, @RequestParam(value = "conversion_id", required = false) String conversionId, @RequestParam(value = "first_id", required = false) String firstId, @RequestParam(defaultValue = "20", required = false) int limit);


    /**
     * 获取会话列表
     *
     * @param user   用户
     * @param lastId （选填）当前页最后面一条记录的 ID，默认 null
     * @param limit  （选填）一次请求返回多少条记录，默认 20 条，最大 100 条，最小 1 条。
     * @param sortBy （选填）排序字段，默认 -updated_at(按更新时间倒序排列)；可选值：created_at, -created_at, updated_at, -updated_at；字段前面的符号代表顺序或倒序，-代表倒序
     */
    @GetExchange(CONVERSIONS)
    DifyPageResponse<DifyConversion> conversions(@RequestParam String user, @RequestParam(value = "last_id", required = false) String lastId, @RequestParam(defaultValue = "20", required = false) int limit, @RequestParam(value = "sort_by", required = false) String sortBy);

    /**
     * 删除会话
     *
     * @param conversationId 会话id
     * @param userRequest    用户
     * @return 结果
     */
    @DeleteExchange(CONVERSION_BY_ID)
    DifyResult deleteConversation(@PathVariable String conversationId, @RequestBody DifyUserRequest userRequest);

    /**
     * 会话重命名
     * <p>
     * 对会话进行重命名，会话名称用于显示在支持多会话的客户端上。
     *
     * @param conversationId 会话id
     * @param request        请求
     * @return 会话
     */
    @PostExchange(CONVERSION_NAME)
    DifyConversion updateConversionName(@PathVariable String conversationId, @RequestBody DifyConversionRequest request);

    /**
     * 语音转文字
     *
     * @param file 语音文件。 支持格式：['mp3', 'mp4', 'mpeg', 'mpga', 'm4a', 'wav', 'webm'] 文件大小限制：15MB
     * @param user 用户
     * @return 文字结果
     */
    @PostExchange(value = AUDIO_TO_TEXT, contentType = MediaType.MULTIPART_FORM_DATA_VALUE)
    DifyAudioToText audioToText(@RequestPart("file") Resource file, @RequestPart("user") String user);

    /**
     * 获取应用Meta信息
     * <p>
     * 用于获取工具icon
     *
     * @return icon
     */
    @PostExchange(META)
    DifyMeta meta();

}
