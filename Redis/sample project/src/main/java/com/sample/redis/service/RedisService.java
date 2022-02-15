package com.sample.redis.service;

import com.sample.redis.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;
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

    public void setRedisValue(ChatMessage chatMessage) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        String key = chatMessage.getSender();
        valueOperations.set(key, chatMessage);
    }

    public void getRedisValue(String key) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        ChatMessage chatMessage = (ChatMessage) valueOperations.get(key);
        System.out.println("sender = " + chatMessage.getSender());
        System.out.println("context = " + chatMessage.getContext());
    }
}
