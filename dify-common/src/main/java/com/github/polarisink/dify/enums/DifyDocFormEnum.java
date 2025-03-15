package com.github.polarisink.dify.enums;

/**
 * 索引内容形式
 */
public enum DifyDocFormEnum {
    /**
     * text 文档直接 embedding，经济模式默认为该模式
     */
    text_model,
    /**
     * parent-child 模式
     */
    hierarchical_model,
    /**
     * Q&A 模式：为分片文档生成 Q&A 对，然后对问题进行 embedding
     */
    qa_model
}
