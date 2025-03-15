package com.github.polarisink.dify.response;

/**
 * @param document 文档
 * @param batch    批量
 */
public record DifyDocumentWrapper(DifyDocument document, String batch) {

}
