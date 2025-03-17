package com.github.polarisink.dify.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启dify api
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableConfigurationProperties(DifyProperties.class) // 启用配置属性绑定
@Import({DifyApiAutoConfiguration.class, DifyValidationAutoConfiguration.class})
public @interface EnableDifyApi {
}