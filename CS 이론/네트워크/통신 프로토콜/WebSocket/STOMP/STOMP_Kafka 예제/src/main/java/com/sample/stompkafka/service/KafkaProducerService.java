package com.sample.stompkafka.service;

import com.sample.stompkafka.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    private static final String TOPIC = "testTopic";
    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

    public void sendMessage(ChatMessage chatmessage) {
        System.out.println("kafka producer : " + chatmessage.getMessage());

        kafkaTemplate.send(TOPIC, chatmessage);
    }
}
