package com.example.naming;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanNamingDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = 
            new AnnotationConfigApplicationContext(BeanNamingCfg.class);
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(beanName -> System.out.println(beanName));
    }
}
