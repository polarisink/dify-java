package com.github.polarisink.dify.api;

import com.github.polarisink.dify.response.DifyFile;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.service.annotation.PostExchange;

import static com.github.polarisink.dify.api.DifyRoutes.UPLOAD_FILE;

/**
 * 公共文件上传接口，包内可访问的接口
 */
interface _DifyFileUploadApi {


    /**
     * 上传文件
     * <p>
     * 上传文件并在发送消息时使用，可实现图文多模态理解。 支持您的应用程序所支持的所有格式。 上传的文件仅供当前终端用户使用。
     *
     * @param file 文件
     * @param user 用户
     * @return 文件结果
     */
    @PostExchange(value = UPLOAD_FILE, contentType = MediaType.MULTIPART_FORM_DATA_VALUE)
    DifyFile uploadFile(@RequestPart("file") Resource file, @RequestPart("user") String user);

}
