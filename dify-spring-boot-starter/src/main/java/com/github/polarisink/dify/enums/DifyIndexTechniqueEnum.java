package com.github.polarisink.dify.enums;

/**
 * 索引方式
 */
public enum DifyIndexTechniqueEnum {
    /**
     * 高质量：使用 embedding 模型进行嵌入，构建为向量数据库索引
     */
    high_quality,
    /**
     * 经济：使用 keyword table index 的倒排索引进行构建
     */
    economy

}
