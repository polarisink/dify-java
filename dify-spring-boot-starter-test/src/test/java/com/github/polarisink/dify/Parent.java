package com.github.polarisink.dify;

import lombok.Builder;
import lombok.ToString;

import java.util.Optional;

@ToString
public class Parent {
    private Integer a = 0;
    private Integer b = 0;
    private Integer c = 0;
    private Integer sum;
    private String name;

    @Builder(builderClassName = "b1")
    public Parent(Integer a, Integer b, Integer c, String name) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.sum = Optional.ofNullable(a).orElse(0) + Optional.ofNullable(b).orElse(0) + Optional.ofNullable(c).orElse(0);
        this.name = name;
    }

    @Builder(builderClassName = "b2")
    public Parent(Integer sum, String name) {
        this.sum = sum;
        this.name = name;
    }

    public static b1 builder() {
        return new b1();
    }

    public static b2 builder2() {
        return new b2();
    }
}