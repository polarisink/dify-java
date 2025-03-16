package com.github.polarisink.dify.api;

import static com.github.polarisink.dify.DifyConsts.KEY_PREFIX;

/**
 * dify基础client
 */
abstract class AbstractDifyClient {
    protected String authorization;
    private String baseUrl;
    private String token;

    public AbstractDifyClient(String baseUrl, String token) {
        //对baseUrl进行基础校验
        if (baseUrl == null || baseUrl.isBlank()) {
            throw new IllegalArgumentException("dify baseUrl can not be blank");
        }
        //对baseUrl进行基础校验
        if (!baseUrl.startsWith("http://") && !baseUrl.startsWith("https://")) {
            throw new IllegalArgumentException("dify baseUrl is invalid");
        }
        //对token进行基础校验
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("dify token can not be blank");
        }
        this.baseUrl = baseUrl;
        this.token = token;
        this.authorization = token.startsWith(KEY_PREFIX) ? token : KEY_PREFIX + token;

    }
}
