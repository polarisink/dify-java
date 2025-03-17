package com.github.polarisink.dify.core;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

@Configuration
class DifyComponents {
    /**
     * 默认拦截器
     *
     * @return interceptor
     */
    @Primary
    @Bean("difyInterceptor")
    @ConditionalOnMissingBean(ClientHttpRequestInterceptor.class)
    public ClientHttpRequestInterceptor difyInterceptor() {
        return (request, body, execution) -> execution.execute(request, body);
    }

    /**
     * 默认过滤器
     *
     * @return function
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(ExchangeFilterFunction.class)
    public ExchangeFilterFunction difyFilter() {
        return (request, next) -> next.exchange(request);
    }
}
