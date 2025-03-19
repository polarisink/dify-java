package com.github.polarisink.dify.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.polarisink.dify.enums.DifyFileTypeEnum;
import com.github.polarisink.dify.enums.DifyTransferMethodEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.Assert;

@Getter
@Builder
@ToString
public final class DifyFileRequest {
    /**
     * 支持类型：图片 image（目前仅支持图片格式） 。
     */
    private DifyFileTypeEnum type;
    /**
     * 传递方式:
     * <p>
     * remote_url: 图片地址。
     * local_file: 上传文件
     */
    @JsonProperty("transfer_method")
    private DifyTransferMethodEnum transferMethod;
    /**
     * 图片地址。（仅当传递方式为 remote_url 时）
     */
    private String url;
    /**
     * 上传文件 ID（仅当传递方式为 local_file 时）
     */
    @JsonProperty("upload_file_id")
    private String uploadFileId;

    public static DifyFileRequestBuilder builder() {
        return new DifyFileRequestBuilder() {
            @Override
            public DifyFileRequest build() {
                DifyFileRequest build = super.build();
                Assert.notNull(build.transferMethod, "transferMethod can not be null");
                Assert.isTrue(build.transferMethod == DifyTransferMethodEnum.remote_url && (build.url == null || build.url.isBlank()), "url can not be null when transferMethod is remote_url");
                Assert.isTrue(build.transferMethod == DifyTransferMethodEnum.local_file && (build.uploadFileId == null || build.uploadFileId.isBlank()), "uploadFileId can not be null when transferMethod is local_file");
                return build;
            }
        };
    }
}