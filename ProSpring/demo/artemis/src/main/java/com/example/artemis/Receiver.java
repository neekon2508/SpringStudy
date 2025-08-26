package com.example.artemis;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Receiver {

    @JmsListener(destination = "${spring.artemis.embedded.queues}")
    public void receive(Letter letter) {
        log.info(" >> received letter={}", letter);
    }
}
