package com.github.polarisink.dify.api;

import com.github.polarisink.dify.response.DifyPageResponse;
import com.github.polarisink.dify.request.*;
import com.github.polarisink.dify.response.*;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

import static com.github.polarisink.dify.api.DifyRoutes.*;

/**
 * dify知识库api
 */
public interface DifyDatasetApi {


    /**
     * 通过文本创建文档
     *
     * @param datasetId 知识库id
     * @param request   创建请求
     */
    @PostExchange(CREATE_DOC_BY_TEXT)
    DifyDocumentWrapper createDocByText(@PathVariable String datasetId, @RequestBody DifyDatasetTextRequest request);

    /**
     * 通过文件创建文档
     *
     * @param file        文件
     * @param datasetId   知识库id
     * @param jsonRequest 创建请求,这个json来自于{@link DifyDatasetFileRequest}对象转json字符串的结果
     */
    @PostExchange(CREATE_DOC_BY_FILE)
    DifyDocumentWrapper createDocByFile(@RequestPart("file") Resource file, @PathVariable String datasetId, @RequestPart("data") String jsonRequest);

    /**
     * 创建空知识库
     *
     * @param request 创建请求
     */
    @PostExchange(DATASETS)
    DifyDataset createDataset(@RequestBody DifyDatasetCreateRequest request);

    /**
     * 知识库列表
     *
     * @param page  页码
     * @param limit 页大小
     */
    @GetExchange(DATASETS)
    DifyPageResponse<DifyDataset> datasetPage(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "limit", defaultValue = "20") Integer limit);


    /**
     * 删除知识库
     *
     * @param datasetId 知识库id
     */
    @DeleteExchange(DATASETS_ID)
    void deleteDataset(@PathVariable String datasetId);


    /**
     * 通过文本更新文档
     *
     * @param datasetId  知识库id
     * @param documentId 文档id
     * @param request    请求
     * @return 结果
     */
    @PostExchange(UPDATE_DOC_BY_TEXT)
    DifyDocumentWrapper updateDocByText(@PathVariable String datasetId, @PathVariable String documentId, @RequestBody DifyDatasetTextRequest request);

    /**
     * 通过文件更新文档
     * <p>
     *
     * @param datasetId   知识库id
     * @param documentId  文档id
     * @param file        文件
     * @param jsonRequest 创建请求,这个json来自于{@link DifyDatasetFileRequest}对象转json字符串的结果
     */
    @PostExchange(value = UPDATE_DOC_BY_FILE, contentType = MediaType.MULTIPART_FORM_DATA_VALUE)
    DifyDocumentWrapper _updateDocByFile(@PathVariable String datasetId, @PathVariable String documentId, @RequestPart("file") Resource file, @RequestPart("data") String jsonRequest);


    /**
     * 通过文件更新文档
     *
     * @param datasetId  知识库id
     * @param documentId 文档id
     * @param file       文件
     * @param request    请求
     * @return 结果
     */
    /*default DifyDocumentWrapper updateDocByFile(String datasetId, String documentId, MultipartFile file, DifyDatasetFileRequest request) {
        return _updateDocByFile(datasetId, documentId, file, JsonUtil.toJson(request));
    }*/

    /**
     * 获取文档嵌入状态（进度）
     *
     * @param datasetId 知识库 ID
     * @param batch     上传文档的批次号
     * @return 结果
     */
    @GetExchange(DOC_INDEX_STATUS)
    DifyIndexingStatus docIndexingStatus(@PathVariable String datasetId, @PathVariable String batch);


    /**
     * 删除文档
     * 删除指定知识库的文档
     *
     * @param datasetId  知识库id
     * @param documentId 文档id
     */
    @DeleteExchange(DELETE_DOC)
    DifyResult deleteDocument(@PathVariable String datasetId, @PathVariable String documentId);

    /**
     * 知识库文档列表
     *
     * @return 分页结果
     */
    @GetExchange(PAGE_DOC)
    DifyPageResponse<DifyDocument> pageDocument(@PathVariable("datasetId") String datasetId, DifyPageRequest request);

    /**
     * 新增分段
     *
     * @param datasetId  知识库id
     * @param documentId 文档id
     * @param request    分段请求
     * @return 结果
     */
    @PostExchange(DOC_SEGMENT)
    DifySegmentWrapper addSegment(@PathVariable String datasetId, @PathVariable String documentId, @RequestBody DifySegmentAddListRequest request);


    /**
     * 查询文档分段
     *
     * @param datasetId  知识库id
     * @param documentId 文档id
     * @param keyword    关键字，可选
     * @param status     搜索状态，completed
     * @return 结果
     */
    @GetExchange(DOC_SEGMENT)
    DifySegmentWrapper querySegment(@PathVariable String datasetId, @PathVariable String documentId, @RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "status", required = false) String status);

    /**
     * 删除文档分段
     *
     * @param datasetId  知识库id
     * @param documentId 文档id
     * @param segmentId  分段id
     * @return 是否成功
     */
    @DeleteExchange(DOC_DELETE_UPDATE_SEGMENT)
    DifyResult deleteSegment(@PathVariable String datasetId, @PathVariable String documentId, @PathVariable String segmentId);

    /**
     * 更新文档分段
     *
     * @param datasetId  知识库id
     * @param documentId 文档id
     * @param segmentId  分段id
     * @return 结果
     */
    @PostExchange(DOC_DELETE_UPDATE_SEGMENT)
    DifySegmentWrapper updateSegment(@PathVariable String datasetId, @PathVariable String documentId, @PathVariable String segmentId, @RequestBody DifySegmentUpdateRequest request);

    /**
     * 获取上传文件
     *
     * @param datasetId  知识库id
     * @param documentId 文档id
     * @return 文件
     */
    @GetExchange(DOC_UPLOAD_FILE)
    DifyFile getUploadFile(@PathVariable String datasetId, @PathVariable String documentId);

    /**
     * 检索知识库
     *
     * @param datasetId 知识库id
     * @param request   请求体
     */
    @PostExchange(DATASET_RETRIEVE)
    DifyRetrieve retrieveDataset(@PathVariable String datasetId, @RequestBody DifyRetrieveRequest request);

}
