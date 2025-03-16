package com.github.polarisink.dify;

import java.time.LocalDate;

/**
 * 网页元数据
 *
 * @param title        页面标题
 * @param url          页面网址
 * @param language     页面语言
 * @param publisher    发布者
 * @param publish_date 发布日期
 * @param author       作者
 * @param topic        主题
 * @param keywords     关键词
 * @param description  描述
 */
public record DifyWebPageMetadata(String title, String url, String language, String publisher, LocalDate publish_date,
                                  String author, String topic, String keywords, String description) {
}
