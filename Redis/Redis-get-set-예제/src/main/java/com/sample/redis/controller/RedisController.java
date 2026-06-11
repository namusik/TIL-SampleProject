package com.sample.redis.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sample.redis.model.ChatMessage;
import com.sample.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
    public String send(@RequestBody ChatMessage chatMessage) throws JsonProcessingException {
        redisService.setRedisValue(chatMessage);

        String key = chatMessage.getSender();
        ChatMessage chatMessage1 = redisService.getRedisValue(key, ChatMessage.class);

        return chatMessage1.getContext();
    }

    @GetMapping("api/session")
    public String getSessionId(HttpSession session) {
        session.setAttribute("name" , "treesick");
        return session.getId();
    }
}
