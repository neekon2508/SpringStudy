package com.example.chap4_boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.chap4_boot.beans.MessageProvider;
import com.example.chap4_boot.beans.MessageRenderer;

@Order(2)
@Component("messageRenderer")
class StandardOutMessageRenderer  implements MessageRenderer, CommandLineRunner{
     private static Logger logger = LoggerFactory.getLogger(StandardOutMessageRenderer.class);
    private MessageProvider messageProvider;
    @Autowired
    @Override
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }
    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }
    @Override
    public void render() {
        logger.info(messageProvider.getMessage());
    }
    @Override
    public void run(String... args) throws Exception {
        render();
    }
}
@Order(1)
@Component
class ConfigurableMessageProvider implements MessageProvider, CommandLineRunner {
    private String message;
    public ConfigurableMessageProvider(@Value("Configurable message") String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public void run(String... args) throws Exception {
        if(args.length > 0)
            message = args[0];
    }
}

@SpringBootApplication
public class WithBeansApplication {
    private static Logger logger = LoggerFactory.getLogger(WithBeansApplication.class);

    public static void main(String[] args) {
       SpringApplication.run(WithBeansApplication.class, args);
    }
}
