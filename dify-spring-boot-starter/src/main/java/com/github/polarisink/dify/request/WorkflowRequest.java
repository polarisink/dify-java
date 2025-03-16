package com.github.polarisink.dify.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

// 请求体定义
public record WorkflowRequest(
        Map<String, Object> inputs,    // 动态键值对参数
        String response_mode,         // 固定为 "streaming" 或 "blocking"
        String user,                  // 用户唯一标识
        List<FileInput> files          // 文件列表（可选）
) {

    // 文件对象定义
    public record FileInput(
            String type,                  // 固定为 "image"
            String transfer_method,       // "remote_url" 或 "local_file"
            String url,                   // 图片地址（remote_url 时）
            String upload_file_id         // 上传文件 ID（local_file 时）
    ) {
    }

    // 通用流式响应事件基类（支持多态解析）
    public abstract class WorkflowEvent {
        private String task_id;
        private String workflow_run_id;
        private String event;

        // Getters and Setters
    }

    // 具体事件类型定义（示例：workflow_started）
    public class WorkflowStartedEvent extends WorkflowEvent {
        private WorkflowStartedData data;

        @Data
        public static class WorkflowStartedData {
            private String id;
            private String workflow_id;
            private int sequence_number;
            private String created_at;
        }
    }

// 其他事件类型（node_started, node_finished 等）类似定义...
}
