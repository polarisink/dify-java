package com.github.polarisink.dify.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.polarisink.dify.enums.DifyDocFormEnum;
import com.github.polarisink.dify.enums.DifyDocTypeEnum;
import com.github.polarisink.dify.enums.DifyIndexTechniqueEnum;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * dify知识库操作基类
 */
@Getter
@ToString
@SuperBuilder
public class DifyDatasetBasicRequest {
    /**
     * 文档类型
     */
    @JsonProperty("doc_type")
    private DifyDocTypeEnum docType;
    /**
     * 文档元数据
     */
    @JsonProperty("doc_metadata")
    private Object docMetadata;
    /**
     * 索引方式
     */
    @JsonProperty("indexing_technique")
    private DifyIndexTechniqueEnum indexingTechnique;
    /**
     * 索引内容形式
     */
    @JsonProperty("doc_form")
    private DifyDocFormEnum docForm;
    /**
     * 在 Q&A 模式下，指定文档的语言，例如：English、Chinese
     */
    @JsonProperty("doc_language")
    private String docLanguage;
    /**
     * 处理规则
     */
    @JsonProperty("process_rule")
    private DifyProcessRuleRequest processRule;
}
