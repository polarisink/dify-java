package com.github.polarisink.dify.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public final class DifyTextRequest {

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
     * 文件列表，适用于传入文件结合文本理解并回答问题，仅当模型支持 Vision 能力时可用
     * 具体请看官方文档
     */
    private List<DifyFileRequest> files;

    public static DifyTextRequest.DifyTextRequestBuilder builder() {
        return new DifyTextRequest.DifyTextRequestBuilder() {
            @Override
            public DifyTextRequest build() {
                DifyTextRequest build = super.build();
                Assert.hasText(build.user, "user can not be blank");
                Map<String, Object> inputs1 = build.inputs;
                Assert.notEmpty(inputs1, "inputs can not be empty");
                Assert.isTrue(inputs1.containsKey("query"), "inputs must contain query");
                Object query = inputs1.get("query");
                Assert.isTrue(query != null && !query.toString().isBlank(), "query value can not be blank");
                return build;
            }

        };
    }

}
