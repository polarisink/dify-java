package com.github.polarisink.dify;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启dify api
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({DifyApiAutoConfiguration.class})
@interface EnableDifyApi {
}