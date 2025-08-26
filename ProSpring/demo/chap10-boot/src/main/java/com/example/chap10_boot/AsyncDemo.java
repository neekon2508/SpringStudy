package com.example.chap10_boot;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.chap10_boot.config.AsyncConfig;
import com.example.chap10_boot.service.AsyncService;


@SpringBootApplication
public class AsyncDemo {
    private static final Logger  LOGGER = LoggerFactory.getLogger(AsyncDemo.class);
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        try (var ctx = new AnnotationConfigApplicationContext(AsyncConfig.class)) {
            var asyncService = ctx.getBean("asyncService", AsyncService.class);

            for (int i =0; i < 5; i++)
                asyncService.asyncTask();
            var result1 = asyncService.asyncWithReturn("John Mayer");
            var result2 = asyncService.asyncWithReturn("Eric Clapton");
            var result3 = asyncService.asyncWithReturn("BB King");
            Thread.sleep(6000);

            LOGGER.info(" >> Result1: " + result1.get());
            LOGGER.info(" >> Result1: " + result2.get());
            LOGGER.info(" >> Result1: " + result3.get());
    
            System.in.read();
        }

        
    }
}
