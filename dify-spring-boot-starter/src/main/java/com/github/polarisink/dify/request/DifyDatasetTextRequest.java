package com.github.polarisink.dify.request;

import com.github.polarisink.dify.enums.DifyDocFormEnum;
import com.github.polarisink.dify.enums.DifyDocTypeEnum;
import com.github.polarisink.dify.enums.DifyIndexTechniqueEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 通过文本创建或更新文档请求
 */
@ToString(callSuper = true)
@Getter
public final class DifyDatasetTextRequest extends DifyDatasetBasicRequest {
    /**
     * 文档名称
     */
    private String name;
    /**
     * 文档内容
     */
    private String text;

    @Builder
    public DifyDatasetTextRequest(String name, String text, DifyDocTypeEnum docType, Object docMetadata, DifyIndexTechniqueEnum indexingTechnique, DifyDocFormEnum docForm, String docLanguage, DifyProcessRuleRequest processRule) {
        super(docType, docMetadata, indexingTechnique, docForm, docLanguage, processRule);
        this.name = name;
        this.text = text;
    }
}
