package com.github.polarisink.dify.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

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
        Assert.hasText(baseUrl, "dify baseUrl is can not be blank");
        Assert.isTrue(baseUrl.startsWith("http://") || baseUrl.startsWith("https://"), "dify baseUrl is invalid");
        log.info("Dify Api enabled...");
    }
}