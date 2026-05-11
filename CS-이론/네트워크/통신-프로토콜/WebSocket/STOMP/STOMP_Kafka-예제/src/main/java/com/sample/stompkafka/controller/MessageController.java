package com.sample.stompkafka.controller;

import com.sample.stompkafka.model.ChatMessage;
import com.sample.stompkafka.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final KafkaProducerService producerService;

    @MessageMapping("/chat/message")
    public void enter(ChatMessage message) {
        producerService.sendMessage(message);
    }
}
