package com.github.polarisink.dify.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.polarisink.dify.enums.DifyPreProcessRuleIdEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

/**
 *
 */
@Getter
@ToString
@Builder
public class DifyProcessRuleRequest {
    /**
     * 自定义规则（自动模式下，该字段为空）
     */
    private Rules rules;
    /**
     * 清洗、分段模式 ，automatic 自动 / custom 自定义
     */
    private String mode;


    /**
     * 处理规则
     */
    @Getter
    @ToString
    @Builder
    public static class Rules {
        /**
         * 预处理规则
         */
        @JsonProperty("pre_processing_rules")
        private Collection<RuleItem> preProcessingRules;
        /**
         * 分段规则
         */
        private RuleSegmentation segmentation;
        @JsonProperty("parent_mode")
        private String parentMode;
        /**
         * 子分段规则
         */
        @JsonProperty("subchunk_segmentation")
        private RuleSegmentation subchunkSegmentation;


    }

    /**
     * 预处理规则
     */
    @Builder
    @Getter
    @ToString
    public static class RuleItem {
        /**
         * 预处理规则的唯一标识符
         */
        private DifyPreProcessRuleIdEnum id;
        /**
         * 是否选中该规则，不传入文档 ID 时代表默认值
         */
        private Boolean enabled;


    }


    /**
     * 分段规则,当为子分段规则时有chunkOverlap字段
     */
    @Builder
    @Getter
    @ToString
    public static class RuleSegmentation {
        /**
         * 分段标识符，目前仅允许设置一个分隔符。默认为 ***
         */
        private String separator;
        /**
         * 最大长度 (token) ,默认为 1000,需要校验小于父级的长度
         */
        @JsonProperty("max_tokens")
        private Integer maxTokens;
        /**
         * 最大长度 (token) ,默认为 1000,需要校验小于父级的长度
         */
        @JsonProperty("chunk_overlap ")
        private String chunkOverlap;


    }
}
