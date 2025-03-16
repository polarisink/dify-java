package com.github.polarisink.dify;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.github.polarisink.dify.enums.DifyIndexTechniqueEnum;
import com.github.polarisink.dify.enums.DifyPermissionEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * dify知识库基础属性
 */
@Getter
@ToString
@SuperBuilder
public class DifyDatasetBasicInfo {
    /**
     * 知识库名称（必填）
     */
    private String name;
    /**
     * 知识库描述（选填）
     */
    private String description;
    /**
     * 权限（选填，默认 only_me）
     */
    @Builder.Default
    private DifyPermissionEnum permission = DifyPermissionEnum.only_me;
    /**
     * Provider（选填，默认 vendor）
     */
    @Builder.Default
    private String provider = "vendor";
    /**
     * 索引模式（选填，建议填写）
     */
    @JsonAlias("indexing_technique")
    private DifyIndexTechniqueEnum indexingTechnique;

    public static DifyDatasetBasicInfoBuilder builder() {
        return new DifyDatasetBasicInfoBuilder() {
            @Override
            public DifyDatasetBasicInfo build() {
                DifyDatasetBasicInfo info = new DifyDatasetBasicInfo(this);
                if (info.name == null || info.name.isBlank()) {
                    throw new IllegalArgumentException("DifyDatasetBasicInfo#name can not be blank");
                }
                return info;
            }

            @Override
            protected DifyDatasetBasicInfoBuilder self() {
                return this;
            }
        };
    }
}
