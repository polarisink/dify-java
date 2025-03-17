package com.github.polarisink.dify.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

/**
 * 动态校验baseUrl
 */
@Slf4j
class DifyPropertiesValidator implements InitializingBean {
    private final DifyProperties properties;

    public DifyPropertiesValidator(DifyProperties properties) {
        this.properties = properties;
    }

    @Override
    public void afterPropertiesSet() {
        String baseUrl = properties.getBaseUrl();
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalArgumentException("dify baseUrl is can not be blank");
        }
        if (!baseUrl.startsWith("http://") && !baseUrl.startsWith("https://")) {
            throw new IllegalArgumentException("dify baseUrl is invalid");
        }
        log.info("Dify Api enabled...");
    }
}