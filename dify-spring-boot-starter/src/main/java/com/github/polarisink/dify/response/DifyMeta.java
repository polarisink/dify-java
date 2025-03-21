package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @param toolIcon 工具图标
 */
public record DifyMeta(@JsonAlias("tool_icons") ToolIcon toolIcon) {
    public record ToolIcon(String dalle2, @JsonAlias("api_tool") ApiTool apiTool) {
    }

    public record ApiTool(String background, String content) {
    }
}
