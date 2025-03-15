package com.github.polarisink.dify.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.polarisink.dify.DifyPageResponse;
import com.github.polarisink.dify.request.*;
import com.github.polarisink.dify.response.*;
import lombok.Builder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class DifyDatasetClient extends BaseDifyClient implements DifyDatasetApi {

    @Builder
    public DifyDatasetClient(String baseUrl, String token, ObjectMapper objectMapper) {
        super(baseUrl, token, objectMapper);
    }

    @Override
    public DifyDocumentWrapper createDocByText(String datasetId, DifyDatasetTextRequest request) {
        return restClient.post().uri(CREATE_DOC_BY_TEXT, datasetId).body(request).retrieve().body(DifyDocumentWrapper.class);
    }

    @Override
    public DifyDocumentWrapper createDocByFile(Resource file, String datasetId, String jsonRequest) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>() {{
            add("file", file);
            add("data", jsonRequest);
        }};
        return restClient.post().uri(CREATE_DOC_BY_FILE, datasetId).body(map).retrieve().body(DifyDocumentWrapper.class);
    }

    @Override
    public DifyDataset createDataset(DifyDatasetCreateRequest request) {
        return restClient.post().uri(DATASETS).body(request).retrieve().body(DifyDataset.class);
    }

    @Override
    public DifyPageResponse<DifyDataset> datasetPage(Integer page, Integer limit) {
        URI uri = UriComponentsBuilder.fromPath(DATASETS).queryParam("page", page).queryParam("limit", limit).build().toUri();
        return restClient.get().uri(uri).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }

    @Override
    public void deleteDataset(String datasetId) {
        restClient.delete().uri(DATASETS_ID, datasetId).retrieve();
    }

    @Override
    public DifyDocumentWrapper updateDocByText(String datasetId, String documentId, DifyDatasetTextRequest request) {
        return restClient.post().uri(UPDATE_DOC_BY_TEXT, datasetId, documentId).body(request).retrieve().body(DifyDocumentWrapper.class);
    }

    @Override
    public DifyDocumentWrapper _updateDocByFile(String datasetId, String documentId, Resource file, String jsonRequest) {
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>() {{
            add("file", file);
            add("data", jsonRequest);
        }};
        return restClient.post().uri(UPDATE_DOC_BY_FILE, datasetId, documentId).contentType(MediaType.MULTIPART_FORM_DATA).body(map).retrieve().body(DifyDocumentWrapper.class);
    }

    @Override
    public DifyIndexingStatus docIndexingStatus(String datasetId, String batch) {
        return restClient.get().uri(DOC_INDEX_STATUS, datasetId, batch).retrieve().body(DifyIndexingStatus.class);
    }

    @Override
    public DifyResult deleteDocument(String datasetId, String documentId) {
        return restClient.delete().uri(DELETE_DOC, datasetId, documentId).retrieve().body(DifyResult.class);
    }

    @Override
    public DifyPageResponse<DifyDocument> pageDocument(String datasetId, DifyPageRequest request) {
        URI uri = UriComponentsBuilder.fromPath(PAGE_DOC)
                .queryParam("page", request.getPage())
                .queryParam("limit", request.getLimit())
                .queryParam("keyword", request.getKeyword())
                .buildAndExpand(datasetId).toUri();
        return restClient.get().uri(uri).retrieve().body(new ParameterizedTypeReference<>() {
        });
    }

    @Override
    public DifySegmentWrapper addSegment(String datasetId, String documentId, DifySegmentAddListRequest request) {
        return restClient.post().uri(DOC_SEGMENT, datasetId, documentId).body(request).retrieve().body(DifySegmentWrapper.class);
    }

    @Override
    public DifySegmentWrapper querySegment(String datasetId, String documentId, String keyword, String status) {
        URI uri = UriComponentsBuilder.fromPath(DOC_SEGMENT).queryParam("keyword", keyword).queryParam("status", status).buildAndExpand(datasetId, documentId).toUri();
        return restClient.get().uri(uri).retrieve().body(DifySegmentWrapper.class);
    }

    @Override
    public DifyResult deleteSegment(String datasetId, String documentId, String segmentId) {
        return restClient.delete().uri(DOC_ADD_UPDATE_SEGMENT, datasetId, documentId, segmentId).retrieve().body(DifyResult.class);
    }

    @Override
    public DifySegmentWrapper updateSegment(String datasetId, String documentId, String segmentId, DifySegmentUpdateRequest request) {
        return restClient.post().uri(DOC_ADD_UPDATE_SEGMENT, datasetId, documentId, segmentId).body(request).retrieve().body(DifySegmentWrapper.class);
    }

    @Override
    public DifyFile getUploadFile(String datasetId, String documentId) {
        return restClient.get().uri(DOC_ADD_UPDATE_SEGMENT, datasetId, documentId).retrieve().body(DifyFile.class);
    }

    @Override
    public DifyRetrieve retrieveDataset(String datasetId, DifyRetrieveRequest request) {
        return restClient.post().uri(DATASET_RETRIEVE, datasetId).body(request).retrieve().body(DifyRetrieve.class);
    }
}
