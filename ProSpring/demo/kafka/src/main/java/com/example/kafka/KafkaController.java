package com.example.kafka;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/kafka")
public class KafkaController {

    private final LetterSender sender;

    @PostMapping(value = "/send")
    public void sendMessageToKafkaTopic(@RequestBody Letter letter) {
        this.sender.send(letter);
    }
    
}
