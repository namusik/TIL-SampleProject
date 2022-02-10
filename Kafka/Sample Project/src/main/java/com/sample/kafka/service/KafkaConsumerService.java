package com.sample.kafka.service;

import com.sample.kafka.model.Chatmessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "wowowo", groupId = "namu")
    public void consume(Chatmessage message) throws IOException {
        System.out.println("name = " + message.getName());
        System.out.println("consume message = " + message.getContext());
    }

}
