package com.github.polarisink.dify.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.github.polarisink.dify.enums.DifyTransferMethodEnum;

import java.util.Collection;

public record DifyParameter(String introduction, @JsonAlias("user_input_form") Collection<UserInputForm> userInputForm,
                            @JsonAlias("file_upload") FileUpload fileUpload,
                            @JsonAlias("system_parameters") SystemParameters systemParameters) {
    public record UserInputForm(@JsonAlias("text-input") TextInput textInput) {
    }

    public record TextInput(String label, String variable, Boolean required,
                            @JsonAlias("max_length") Integer maxLength, @JsonAlias("default") String defaults) {
    }

    public record FileUpload(DifyImage images) {
    }

    public record DifyImage(Boolean enabled, @JsonAlias("number_limits") Integer numberLimits,
                            @JsonAlias("transfer_methods") Collection<DifyTransferMethodEnum> transferMethods) {
    }

    public record SystemParameters(@JsonAlias("file_size_limit") Integer fileSizeLimit,
                                   @JsonAlias("image_file_size_limit") Integer imageFileSizeLimit,
                                   @JsonAlias("audio_file_size_limit") Integer audioFileSizeLimit,
                                   @JsonAlias("video_file_size_limit") Integer videoFileSizeLimit) {
    }
}
