package com.github.polarisink.dify;

import java.time.LocalDate;

/**
 * 图书元数据
 *
 * @param title          书名
 * @param author         作者
 * @param language       语言
 * @param publisher      出版社
 * @param published_date 出版日期
 * @param isbn           isbn号码
 * @param category       图书分类
 */
public record DifyBookMetadata(String title, String author, String language, String publisher, LocalDate published_date,
                               Integer isbn, String category) {
}
