package com.example.artemis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Sender {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    private final JmsTemplate jmsTemplate;

    @PostConstruct
    public void init() {
        jmsTemplate.setDeliveryDelay(2000L);
    }

    @Value("${spring.artemis.embedded.queues}")
    private String queueName;

    public void send(Letter letter) {
        LOGGER.info(" >> sending letter={}", letter);
        jmsTemplate.convertAndSend(queueName, letter);
    }
}
