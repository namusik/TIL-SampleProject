package com.sample2.redis.controller;

import com.sample2.redis.model.ChatMessage;
import com.sample2.redis.service.RedisPubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RedisController {
    private final RedisPubService redisPubService;

    @PostMapping("api/chat")
    public String pubSub(@RequestBody ChatMessage chatMessage) {
        //메시지 보내기
        redisPubService.sendMessage(chatMessage);

        return "success";
    }
}
