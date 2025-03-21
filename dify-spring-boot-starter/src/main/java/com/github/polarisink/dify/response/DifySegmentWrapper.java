package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.github.polarisink.dify.enums.DifyDocFormEnum;

import java.util.Collection;

/**
 * dify分段响应
 *
 * @param data    数据
 * @param docForm form
 */
public record DifySegmentWrapper(Collection<DifySegment> data,
                                 @JsonAlias("doc_form") DifyDocFormEnum docForm) {
}
