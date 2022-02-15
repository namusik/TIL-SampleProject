package com.sample2.redis.service;

import com.sample2.redis.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisPubService {
    private final RedisTemplate<String, Object> redisTemplate;

    public void sendMessage(ChatMessage chatMessage) {
        redisTemplate.convertAndSend("topic1", chatMessage);

    }
}
