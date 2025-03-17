package com.github.polarisink.dify;

import com.github.polarisink.dify.core.EnableDifyApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDifyApi
@SpringBootApplication
public class DifySpringbootStarterTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DifySpringbootStarterTestApplication.class, args);
    }
}
