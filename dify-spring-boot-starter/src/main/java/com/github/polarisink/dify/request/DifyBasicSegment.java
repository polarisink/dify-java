package com.github.polarisink.dify.request;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * dify分段新增请求
 */
@ToString
@Getter
@SuperBuilder
public class DifyBasicSegment {
    /**
     * 文本内容/问题内容，必填
     */
    private String content;
    /**
     * 答案内容，非必填，如果知识库的模式为 Q&A 模式则传值
     */
    private String answer;
    /**
     * 关键字，非必填
     */
    private Collection<String> keywords;

    public static DifyBasicSegmentBuilder builder() {
        return new DifyBasicSegmentBuilder() {

            @Override
            public DifyBasicSegment build() {
                DifyBasicSegment build = self().build();
                Assert.hasText(build.content, "content can not be blank");
                return build;
            }

            @Override
            protected DifyBasicSegmentBuilder self() {
                return this;
            }
        };
    }


}