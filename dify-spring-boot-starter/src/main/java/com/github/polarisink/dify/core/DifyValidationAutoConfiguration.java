package com.github.polarisink.dify.core;

import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Configuration
@Conditional(DifyValidationAutoConfiguration.EnableDifyApiCondition.class)
public class DifyValidationAutoConfiguration {

    @Bean
    public DifyPropertiesValidator difyPropertiesValidator(DifyProperties properties) {
        return new DifyPropertiesValidator(properties);
    }

    public static class EnableDifyApiCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            // 检测是否有类被 @EnableDifyApi 注解
            return !context.getBeanFactory().getBeansWithAnnotation(EnableDifyApi.class).isEmpty();
        }
    }
}