package com.github.polarisink.dify.api;

/**
 * dify路由
 */
interface DifyRoutes {
    // 反馈
    String MESSAGE_FEEDBACK = "/messages/{messageId}/feedbacks";

    // 文件上传
    String UPLOAD_FILE = "/files/upload";

    // 信息
    String INFO = "/info";

    // 参数
    String PARAMETERS = "/parameters";

    // 聊天
    String COMPLETION_MESSAGES = "/completion-messages";

    // 停止消息
    String STOP_MESSAGES = "/completion-messages/{taskId}/stop";

    /**
     * 工作流相关路由
     * =======================================================================================================
     * 运行工作流
     */
    String RUN_WORKFLOW = "/workflows/run";

    // 工作流信息
    String WORKFLOW_INFO = "/workflows/run/{workflowId}";

    // 停止工作流
    String STOP_WORKFLOW = "/workflows/run/{workflowId}";

    // 工作流日志
    String WORKFLOW_LOGS = "/workflows/logs";

    // 文字转音频
    String TEXT_TO_AUDIO = "/text-to-audio";

    /**
     * 聊天相关路由
     * =======================================================================================================
     * 聊天消息
     */
    String CHAT_MESSAGES = "/chat-messages";

    //停止聊天相应
    String STOP_CHAT_MESSAGES = "/chat-messages/{taskId}/stop";

    // 建议
    String MESSAGES_SUGGESTED = "/messages/{messageId}/suggested";
    // 获取会话历史消息
    String MESSAGES = "/messages";

    // 获取会话列表
    String CONVERSIONS = "/conversions";

    // 删除会话
    String CONVERSION_BY_ID = "/conversations/{conversationId}";

    // 会话重命名
    String CONVERSION_NAME = "/conversations/{conversationId}/name";

    // 音频转文字
    String AUDIO_TO_TEXT = "/audio-to-text";

    // 元数据
    String META = "/meta";

    /**
     * 知识库路由
     * =======================================================================================================
     * 通过文本创建文档
     */
    String CREATE_DOC_BY_TEXT = "/datasets/{datasetId}/document/create-by-text";

    // 通过文件创建文档
    String CREATE_DOC_BY_FILE = "/datasets/{datasetId}/document/create-by-file";

    // 知识库
    String DATASETS = "/datasets";

    // 通过id删除知识库
    String DATASETS_ID = "/datasets/{datasetId}";

    // 通过文本更新文档
    String UPDATE_DOC_BY_TEXT = "/datasets/{datasetId}/documents/{documentId}/update-by-text";

    // 通过文件更新文档
    String UPDATE_DOC_BY_FILE = "/datasets/{datasetId}/documents/{documentId}/update-by-file";

    // 文档索引状态
    String DOC_INDEX_STATUS = "/datasets/{datasetId}/documents/{batch}/indexing-status";

    // 删除文档
    String DELETE_DOC = "/datasets/{datasetId}/documents/{documentId}";

    // 文档分页
    String PAGE_DOC = "/datasets/{datasetId}/documents";

    // 文档分段
    String DOC_SEGMENT = "/datasets/{datasetId}/documents/{documentId}/segments";

    // 操作文档
    String DOC_DELETE_UPDATE_SEGMENT = "/datasets/{datasetId}/documents/{documentId}/segments/{segmentId}";

    // 获取文档上传的文件
    String DOC_UPLOAD_FILE = "/datasets/{datasetId}/documents/{documentId}/upload-file";

    // 检索知识库
    String DATASET_RETRIEVE = "/datasets/{datasetId}/retrieve";
}
