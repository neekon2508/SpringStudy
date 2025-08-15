package com.example.chap4_boot.beans;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class BeansTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    MessageRenderer messageRenderer;

    @Autowired
    MessageProvider messageProvider;

    @Test
    void contextLoaded() {
        assertNotNull(context);
    }

    @Test
    void renderTest() {
        assertAll("messageTest", 
        () -> assertNotNull(messageRenderer),
        () -> assertNotNull(messageProvider),
        () -> assertEquals(messageProvider, messageRenderer.getMessageProvider()));

        messageRenderer.render();
    }
}
