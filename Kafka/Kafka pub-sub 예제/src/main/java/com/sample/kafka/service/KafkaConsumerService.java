package com.sample.kafka.service;

import com.sample.kafka.model.Chatmessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "testTopic", groupId = "testgroup", containerFactory = "kafkaListener")
    public void consume(Chatmessage message){
        System.out.println("name = " + message.getSender());
        System.out.println("consume message = " + message.getContext());
    }

}
