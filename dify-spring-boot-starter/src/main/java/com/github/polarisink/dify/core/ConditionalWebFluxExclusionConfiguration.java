package com.github.polarisink.dify.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * 此配置类仅在 MVC 环境下生效，并触发排除 WebFluxAutoConfiguration
 */
@Configuration
@ConditionalOnClass(name = "org.springframework.web.servlet.DispatcherServlet")
@EnableAutoConfiguration(exclude = WebFluxAutoConfiguration.class)
public class ConditionalWebFluxExclusionConfiguration {
}