package com.github.polarisink.difytest;

public class BuilderTest {

    public static void main(String[] args) {
        System.out.println(Parent.builder().a(1).b(1).build());
        System.out.println(Parent.builder2().sum(1).name("1").build());
    }


}
