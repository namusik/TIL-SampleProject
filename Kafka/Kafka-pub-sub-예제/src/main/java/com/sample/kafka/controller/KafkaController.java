package com.sample.kafka.controller;

import com.sample.kafka.model.Chatmessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.sample.kafka.service.KafkaProducerService;

@RestController
@RequiredArgsConstructor
public class KafkaController {
    private final KafkaProducerService producerService;

    @PostMapping("/kafka")
    public String sendMessage(@RequestBody Chatmessage chatmessage) {
        System.out.println("chatmessage = " + chatmessage);
        producerService.sendMessage(chatmessage);

        return "success";
    }
}
