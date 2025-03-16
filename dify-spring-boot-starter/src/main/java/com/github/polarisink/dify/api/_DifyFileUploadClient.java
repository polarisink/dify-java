package com.github.polarisink.dify.api;

import com.github.polarisink.dify.response.DifyFile;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import static com.github.polarisink.dify.api.DifyRoutes.UPLOAD_FILE;

/**
 * 文件上传客户端
 */
@RequiredArgsConstructor
class _DifyFileUploadClient implements _DifyFileUploadApi {
    private final RestClient restClient;

    @Override
    public DifyFile uploadFile(Resource file, String user) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>() {{
            add("file", file);
            add("user", user);
        }};
        return restClient.post().uri(UPLOAD_FILE).contentType(MediaType.MULTIPART_FORM_DATA).body(map).retrieve().body(DifyFile.class);
    }
}
