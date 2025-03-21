package com.github.polarisink.dify.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.polarisink.dify.request.*;
import com.github.polarisink.dify.response.*;
import lombok.Builder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import static com.github.polarisink.dify.api.DifyRoutes.*;

/**
 * dify知识库客户端
 */
public class DifyDatasetClient extends AbstractDifyClient implements DifyDatasetApi {


    @Builder(builderClassName = "DifyDatasetClientCustomBuilder", builderMethodName = "customBuilder")
    public DifyDatasetClient(RestClient restClient, WebClient webClient) {
        super(restClient, webClient);
    }

    @Builder(builderClassName = "DifyDatasetClientBuilder")
    public DifyDatasetClient(String baseUrl, String token, ObjectMapper objectMapper, ClientHttpRequestInterceptor interceptor, ExchangeFilterFunction filter) {
        super(baseUrl, token, objectMapper, interceptor, filter);
    }

    @Override
    public DifyDocumentWrapper createDocByText(String datasetId, DifyDatasetTextRequest request) {
        Assert.hasText(datasetId, "datasetId can not be blank");
        Assert.notNull(request, "request can not be null");
        return restClient.post().uri(CREATE_DOC_BY_TEXT, datasetId).body(request).retrieve().body(DifyDocumentWrapper.class);
    }

    @Override
    public DifyDocumentWrapper createDocByFile(Resource file, String datasetId, String jsonRequest) {
        Assert.notNull(file, "file can not be null");
        Assert.hasText(datasetId, "datasetId can not be null");
        Assert.hasText(jsonRequest, "jsonRequest can not be blank");
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>() {{
            add("file", file);
            add("data", jsonRequest);
        }};
        return restClient.post().uri(CREATE_DOC_BY_FILE, datasetId).body(map).retrieve().body(DifyDocumentWrapper.class);
    }

    @Override
    public DifyDataset createDataset(DifyDatasetCreateRequest request) {
        Assert.notNull(request, "request can not be null");
        return restClient.post().uri(DATASETS).body(request).retrieve().body(DifyDataset.class);
    }

    @Override
    public DifyPageResponse<DifyDataset> datasetPage(Integer page, Integer limit) {
        Assert.notNull(page, "page can not be null");
        int l = Optional.ofNullable(limit).orElse(20);
        Assert.isTrue(l >= 1 && l <= 20, "limit must be between 1 and 20");
        String uri = UriComponentsBuilder.fromUriString(DATASETS).queryParam("page", page).queryParam("limit", l).build().toUri().toString();
        return restClient.get().uri(uri).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }

    @Override
    public void deleteDataset(String datasetId) {
        Assert.hasText(datasetId, "datasetId can not be blank");
        restClient.delete().uri(DATASETS_ID, datasetId).retrieve().toBodilessEntity();
    }

    @Override
    public DifyDocumentWrapper updateDocByText(String datasetId, String documentId, DifyDatasetTextRequest request) {
        Assert.hasText(datasetId, "datasetId can not be blank");
        Assert.hasText(documentId, "documentId can not be blank");
        Assert.notNull(request, "request can not be null");
        return restClient.post().uri(UPDATE_DOC_BY_TEXT, datasetId, documentId).body(request).retrieve().body(DifyDocumentWrapper.class);
    }

    @Override
    public DifyDocumentWrapper _updateDocByFile(String datasetId, String documentId, Resource file, String jsonRequest) {
        Assert.hasText(datasetId, "datasetId can not be blank");
        Assert.hasText(documentId, "documentId can not be blank");
        Assert.notNull(file, "file can not be null");
        Assert.hasText(jsonRequest, "jsonRequest can not be null");
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>() {{
            add("file", file);
            add("data", jsonRequest);
        }};
        return restClient.post().uri(UPDATE_DOC_BY_FILE, datasetId, documentId).contentType(MediaType.MULTIPART_FORM_DATA).body(map).retrieve().body(DifyDocumentWrapper.class);
    }

    @Override
    public DifyIndexingStatus docIndexingStatus(String datasetId, String batch) {
        Assert.hasText(datasetId, "datasetId can not be blank");
        Assert.hasText(batch, "batch can not be blank");
        return restClient.get().uri(DOC_INDEX_STATUS, datasetId, batch).retrieve().body(DifyIndexingStatus.class);
    }

    @Override
    public DifyResult deleteDocument(String datasetId, String documentId) {
        Assert.hasText(datasetId, "datasetId can not be blank");
        Assert.hasText(documentId, "documentId can not be blank");
        return restClient.delete().uri(DELETE_DOC, datasetId, documentId).retrieve().body(DifyResult.class);
    }

    @Override
    public DifyPageResponse<DifyDocument> pageDocument(String datasetId, Integer page, Integer limit, String keyword) {
        Assert.hasText(datasetId, "datasetId can not be blank");
        String uri = UriComponentsBuilder.fromUriString(PAGE_DOC)
                //页码可能为空
                .queryParamIfPresent("page", Optional.ofNullable(page))
                //大小默认20
                .queryParam("limit", Optional.ofNullable(page).orElse(20))
                //关键字不必填
                .queryParamIfPresent("keyword", Optional.ofNullable(keyword))
                //绑定知识库id
                .buildAndExpand(datasetId).toUriString();
        return restClient.get().uri(uri).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }

    @Override
    public DifySegmentWrapper addSegment(String datasetId, String documentId, DifySegmentAddListRequest request) {
        Assert.hasText(datasetId, "datasetId can not be blank");
        Assert.hasText(documentId, "documentId can not be blank");
        return restClient.post().uri(DOC_SEGMENT, datasetId, documentId).body(request).retrieve().body(DifySegmentWrapper.class);
    }

    @Override
    public DifySegmentWrapper querySegment(String datasetId, String documentId, String keyword, String status) {
        Assert.hasText(datasetId, "datasetId can not be blank");
        Assert.hasText(documentId, "documentId can not be blank");
        String uri = UriComponentsBuilder.fromUriString(DOC_SEGMENT).queryParamIfPresent("keyword", Optional.ofNullable(keyword)).queryParamIfPresent("status", Optional.ofNullable(status)).buildAndExpand(datasetId, documentId).toUriString();
        return restClient.get().uri(uri).retrieve().body(DifySegmentWrapper.class);
    }

    @Override
    public DifyResult deleteSegment(String datasetId, String documentId, String segmentId) {
        Assert.hasText(datasetId, "datasetId can not be blank");
        Assert.hasText(documentId, "documentId can not be blank");
        Assert.hasText(segmentId, "segmentId can not be blank");
        return restClient.delete().uri(DOC_DELETE_UPDATE_SEGMENT, datasetId, documentId, segmentId).retrieve().body(DifyResult.class);
    }

    @Override
    public DifySegmentWrapper updateSegment(String datasetId, String documentId, String segmentId, DifySegmentUpdateRequest request) {
        Assert.hasText(datasetId, "datasetId can not be blank");
        Assert.hasText(documentId, "documentId can not be blank");
        Assert.hasText(segmentId, "segmentId can not be blank");
        Assert.notNull(request, "request can not be null");
        return restClient.post().uri(DOC_DELETE_UPDATE_SEGMENT, datasetId, documentId, segmentId).body(request).retrieve().body(DifySegmentWrapper.class);
    }

    @Override
    public DifyFile getUploadFile(String datasetId, String documentId) {
        Assert.hasText(datasetId, "datasetId can not be blank");
        Assert.hasText(documentId, "segmentId can not be blank");
        return restClient.get().uri(DOC_DELETE_UPDATE_SEGMENT, datasetId, documentId).retrieve().body(DifyFile.class);
    }

    @Override
    public DifyRetrieve retrieveDataset(String datasetId, DifyRetrieveRequest request) {
        Assert.hasText(datasetId, "datasetId can not be blank");
        Assert.notNull(request, "request can not be null");
        return restClient.post().uri(DATASET_RETRIEVE, datasetId).body(request).retrieve().body(DifyRetrieve.class);
    }
}
