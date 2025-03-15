package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @param toolIcon 工具图标
 */
public record DifyMeta(@JsonProperty("tool_icons") ToolIcon toolIcon) {
    public record ToolIcon(String dalle2, @JsonProperty("api_tool") ApiTool apiTool) {
    }

    public record ApiTool(String background, String content) {
    }
}
