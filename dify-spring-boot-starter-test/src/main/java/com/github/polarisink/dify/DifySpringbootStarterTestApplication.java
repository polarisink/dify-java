package com.github.polarisink.dify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;

@SpringBootApplication(exclude = WebFluxAutoConfiguration.class)
public class DifySpringbootStarterTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DifySpringbootStarterTestApplication.class, args);
    }

}
