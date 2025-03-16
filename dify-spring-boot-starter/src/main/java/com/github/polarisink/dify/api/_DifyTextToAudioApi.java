package com.github.polarisink.dify.api;

import org.springframework.core.io.Resource;
import org.springframework.web.service.annotation.PostExchange;

import static com.github.polarisink.dify.DifyConsts.AUDIO_WAV;
import static com.github.polarisink.dify.api.DifyRoutes.TEXT_TO_AUDIO;

/**
 * 公共的文字转语音接口
 */
public interface _DifyTextToAudioApi {


    /**
     * 文字转语音
     *
     * @param messageId Dify 生成的文本消息，那么直接传递生成的message-id 即可，后台会通过 message_id 查找相应的内容直接合成语音信息。如果同时传 message_id 和 text，优先使用 message_id
     * @param text      语音生成内容。如果没有传 message-id的话，则会使用这个字段的内容
     * @param user      用户标识，由开发者定义规则，需保证用户标识在应用内唯一
     * @return 语音数据
     */
    @PostExchange(value = TEXT_TO_AUDIO, accept = {AUDIO_WAV})
    Resource textToAudio(String messageId, String text, String user);
}
