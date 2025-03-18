package com.github.polarisink.dify.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public final class DifyChatRequest {
    /**
     * 用户输入/提问内容。
     */
    private String query;

    /**
     * 允许传入 App 定义的各变量值
     * 如果变量是文件类型，请指定一个包含以下 files 中所述键的对象
     */
    private Map<String, Object> inputs;

    /**
     * 必填，用户标识，用于定义终端用户的身份，方便检索、统计。 由开发者定义规则，需保证用户标识在应用内唯一。
     */
    private String user;

    /**
     * （选填）会话 ID，需要基于之前的聊天记录继续对话，必须传之前消息的 conversation_id。
     */
    @JsonAlias("conversation_id")
    private String conversationId;

    /**
     * （选填）自动生成标题，默认 true。 若设置为 false，则可通过调用会话重命名接口并设置 auto_generate 为 true 实现异步生成标题。
     */
    @Builder.Default
    @JsonProperty("auto_generate_name")
    private Boolean autoGenerateName = true;

    /**
     * 文件列表，适用于传入文件结合文本理解并回答问题，仅当模型支持 Vision 能力时可用
     * 具体请看官方文档
     */
    private List<DifyFileRequest> files;

    public static DifyChatRequestBuilder builder() {
        return new DifyChatRequestBuilder() {
            @Override
            public DifyChatRequest build() {
                DifyChatRequest build = super.build();
                if (build.query == null || build.query.isBlank()) {
                    throw new IllegalArgumentException("DifyChatRequest#query can not be blank");
                }
                if (build.user==null || build.user.isBlank()){
                    throw new IllegalArgumentException("DifyChatRequest#user can not be blank");
                }
                return build;
            }

        };
    }

}
