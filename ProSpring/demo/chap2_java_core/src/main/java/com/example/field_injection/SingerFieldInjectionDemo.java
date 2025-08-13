package com.example.field_injection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingerFieldInjectionDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(Singer.class, Inspiration.class);
        ctx.refresh();

        Singer singerBean = ctx.getBean(Singer.class);
        singerBean.sing();
    }
}
