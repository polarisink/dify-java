package com.github.polarisink.dify.api;

import com.github.polarisink.dify.response.DifyInfo;
import com.github.polarisink.dify.response.DifyParameter;
import org.springframework.web.service.annotation.GetExchange;

import static com.github.polarisink.dify.api.DifyRoutes.INFO;
import static com.github.polarisink.dify.api.DifyRoutes.PARAMETERS;

/**
 * 公共信息和参数接口，包内可访问的接口
 */
interface _DifyInfoParameterApi {


    /**
     * 用于获取应用的基本信息
     *
     * @return 信息
     */
    @GetExchange(INFO)
    DifyInfo info();

    /**
     * 获取应用参数
     * <p>
     * 用于进入页面一开始，获取功能开关、输入参数名称、类型及默认值等使用。
     *
     * @return 参数
     */
    @GetExchange(PARAMETERS)
    DifyParameter parameters();
}
