package com.github.polarisink.dify.enums;

/**
 * 预处理规则的唯一标识符枚举
 */
public enum DifyPreProcessRuleIdEnum {
    /**
     * 替换连续空格、换行符、制表符
     */
    remove_extra_spaces,
    /**
     * 删除 URL、电子邮件地址
     */
    remove_urls_emails

}
