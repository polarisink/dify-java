package com.github.polarisink.dify.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.polarisink.dify.enums.DifyDocFormEnum;
import com.github.polarisink.dify.enums.DifyDocTypeEnum;
import com.github.polarisink.dify.enums.DifyIndexTechniqueEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * dify知识库通过文件创建或更新的请求
 */
@ToString(callSuper = true)
@Getter
public final class DifyDatasetFileRequest extends DifyDatasetBasicRequest {
    /**
     * 源文档 ID（选填）
     * <p>
     * 用于重新上传文档或修改文档清洗、分段配置，缺失的信息从源文档复制
     * 源文档不可为归档的文档
     * 当传入 original_document_id 时，代表文档进行更新操作，process_rule 为可填项目，不填默认使用源文档的分段方式
     * 未传入 original_document_id 时，代表文档进行新增操作，process_rule 为必填
     */
    @JsonProperty("original_document_id")
    private String originalDocumentId;

    @Builder
    public DifyDatasetFileRequest(DifyDocTypeEnum docType, Object docMetadata, DifyIndexTechniqueEnum indexingTechnique, DifyDocFormEnum docForm, String docLanguage, DifyProcessRuleRequest processRule,String originalDocumentId) {
        super(docType, docMetadata, indexingTechnique, docForm, docLanguage, processRule);
        this.originalDocumentId = originalDocumentId;
    }
}
