package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.polarisink.dify.enums.DifyTransferMethodEnum;

import java.util.Collection;
import java.util.List;

public record DifyParameter(String introduction, @JsonProperty("user_input_form") Collection<UserInputForm> userInputForm,
                            @JsonProperty("file_upload") FileUpload fileUpload,
                            @JsonProperty("system_parameters") SystemParameters systemParameters) {
    public record UserInputForm(@JsonProperty("text-input") TextInput textInput) {
    }

    public record TextInput(String label, String variable, Boolean required,
                            @JsonProperty("max_length") Integer maxLength, @JsonProperty("default") String defaults) {
    }

    public record FileUpload(DifyImage images) {
    }

    public record DifyImage(Boolean enabled, @JsonProperty("number_limits") Integer numberLimits,
                            @JsonProperty("transfer_methods") Collection<DifyTransferMethodEnum> transferMethods) {
    }

    public record SystemParameters(@JsonProperty("file_size_limit") Integer fileSizeLimit,
                                   @JsonProperty("image_file_size_limit") Integer imageFileSizeLimit,
                                   @JsonProperty("audio_file_size_limit") Integer audioFileSizeLimit,
                                   @JsonProperty("video_file_size_limit") Integer videoFileSizeLimit) {
    }
}
