package com.example.chap10_boot.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.example.chap10_boot.service.AsyncService;
import com.example.chap10_boot.service.AsyncServiceImpl;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer{

   @Override
   public Executor getAsyncExecutor() {
      var tpts = new ThreadPoolTaskExecutor();
      tpts.setCorePoolSize(2);
      tpts.setMaxPoolSize(10);
      tpts.setThreadNamePrefix("tpte2-");
      tpts.setQueueCapacity(5);
      tpts.initialize();
      return tpts;
   }


    @Bean
    public AsyncService asyncService() {
        return new AsyncServiceImpl();
    }
}
