package com.github.polarisink.difytest.spring;

import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class UriTest {
    @Test
    void toUri(){
        UriComponents build = UriComponentsBuilder.fromUriString("lqs").queryParam("page", 1).build();
        UriComponents build2 = UriComponentsBuilder.fromPath("lqs").queryParam("page", 1).build();
        System.out.println(build);
        System.out.println(build.toUri());
        System.out.println(build.toUriString());
        System.out.println(build2);
        System.out.println(build2.toUri());
        System.out.println(build2.toUriString());
    }
}
