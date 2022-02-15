package com.sample.redis.controller;

import com.sample.redis.model.ChatMessage;
import com.sample.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    @PostMapping("api/redisStringTest")
    public String sendString(@RequestBody ChatMessage chatMessage) {
        redisService.setRedisStringValue(chatMessage);

        redisService.getRedisStringValue("sender");
        redisService.getRedisStringValue("context");

        return "success";
    }

    @PostMapping("api/redisTest")
    public String send(@RequestBody ChatMessage chatMessage) {
        redisService.setRedisValue(chatMessage);

        String key = chatMessage.getSender();
        redisService.getRedisValue(key);

        return "success";
    }
}
