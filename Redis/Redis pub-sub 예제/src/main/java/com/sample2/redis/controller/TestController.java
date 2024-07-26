package com.sample2.redis.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class TestController {
    @PostMapping("/webhook")
    public Map<String, Object> getWebhook(@RequestBody Map<String, Object> hookRequest) {
      log.info("webhook request: {}", hookRequest);
      return hookRequest;
    }
}
