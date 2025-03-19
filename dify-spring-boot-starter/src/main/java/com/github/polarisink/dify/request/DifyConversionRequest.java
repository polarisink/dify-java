package com.github.polarisink.dify.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

@Getter
@Builder
public final class DifyConversionRequest {
    private String name;
    @JsonProperty("auto_generate")
    private Boolean autoGenerate;
    private String user;

    public static DifyConversionRequestBuilder builder() {
        return new DifyConversionRequestBuilder() {
            @Override
            public DifyConversionRequest build() {
                DifyConversionRequest build = super.build();
                Assert.hasText(build.name, "name can not be blank");
                return build;
            }

        };
    }


}
