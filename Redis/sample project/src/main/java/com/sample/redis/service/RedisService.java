package com.sample.redis.service;

import com.sample.redis.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate stringRedisTemplate;

    public void setRedisStringValue(ChatMessage chatMessage) {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.set("sender", chatMessage.getSender());
        stringValueOperations.set("context", chatMessage.getContext());
    }

    public void getRedisStringValue(String key) {

        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        System.out.println(key +" : " + stringValueOperations.get(key));
    }
}
