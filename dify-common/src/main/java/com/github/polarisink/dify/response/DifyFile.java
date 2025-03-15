package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * dify文件
 */
public record DifyFile(String id, String name, Integer size, String extension, String url,
                       @JsonAlias("download_url") String downloadUrl, @JsonAlias("mime_type") String mimeType,
                       @JsonAlias("created_by") String createdBy, @JsonAlias("created_at") Integer createdAt) {
}
