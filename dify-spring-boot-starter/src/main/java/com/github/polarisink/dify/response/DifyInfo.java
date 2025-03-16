package com.github.polarisink.dify.response;

import java.util.List;

public record DifyInfo(String name, String description, List<String> tags) {
}
