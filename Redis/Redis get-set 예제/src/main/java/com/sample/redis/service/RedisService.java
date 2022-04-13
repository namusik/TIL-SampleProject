package com.sample.redis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    public void setRedisStringValue(ChatMessage chatMessage) {
        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        stringValueOperations.set("sender", chatMessage.getSender());
        stringValueOperations.set("context", chatMessage.getContext());
    }

    public void getRedisStringValue(String key) {

        ValueOperations<String, String> stringValueOperations = stringRedisTemplate.opsForValue();
        System.out.println(key +" : " + stringValueOperations.get(key));
    }

    //직접 만든 redisTemplate 사용
    public void setRedisValue(ChatMessage chatMessage) throws JsonProcessingException {
        String key = chatMessage.getSender();
        redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(chatMessage));
    }

    public <T> T  getRedisValue(String key, Class<T> classType) throws JsonProcessingException {
        String redisValue = (String)redisTemplate.opsForValue().get(key);

        return objectMapper.readValue(redisValue, classType);
    }
}
