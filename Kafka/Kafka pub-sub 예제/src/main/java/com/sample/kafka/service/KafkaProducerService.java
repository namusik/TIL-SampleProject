package com.sample.kafka.service;

import com.sample.kafka.model.Chatmessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private static final String TOPIC = "testTopic";
    private final KafkaTemplate<String, Chatmessage> kafkaTemplate;

    public void sendMessage(Chatmessage chatmessage) {
        System.out.println("chatmessage = " + chatmessage.getContext());

        kafkaTemplate.send(TOPIC, chatmessage);
    }
}
