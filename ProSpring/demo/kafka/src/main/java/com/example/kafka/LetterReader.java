package com.example.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LetterReader {
    @Value("#{kafkaApplication.receivingTopic}")
    private String receivingTopicName;

    @KafkaListener(topics = "#{kafkaApplication.receivingTopic}",
                groupId = "${spring.kafka.consumer.group-id}",
                containerFactory = "kafkaListenerContainerFactory")
    public void consume(@Payload Letter letter) {
        log.info("<<<< [{}] Reading letter -> {}", receivingTopicName, letter);
    }
}
