package com.sample.stompkafka.service;

import com.sample.stompkafka.model.ChatMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "testTopic", groupId = "testgroup", containerFactory = "kafkaListener")
    public void consume(ChatMessage message){
        System.out.println("name = " + message.getSender());
        System.out.println("consume message = " + message.getContext());
    }
}

