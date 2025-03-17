package com.github.polarisink.difytest;

import com.github.polarisink.dify.api.DifyChatApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DifyInitTest implements ApplicationRunner {

    private final DifyChatApi difyChatApi;

    public DifyInitTest(DifyChatApi difyChatApi) {
        this.difyChatApi = difyChatApi;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            System.out.println(difyChatApi.info());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
