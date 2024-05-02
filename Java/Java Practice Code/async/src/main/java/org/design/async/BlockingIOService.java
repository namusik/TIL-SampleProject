package org.design.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class BlockingIOService {
    public String blocking() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        try {
            log.info("블로킹 i/o 시작, 현재 스레드 : {}", Thread.currentThread().getName());
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);
            log.info("블로킹 i/o 완료, 현재 스레드 : {}", Thread.currentThread().getName());
            return response;
        } catch (Exception e) {
            log.error("블로킹 i/o 중 오류 발생");
            return "오류 발생 : " + e.getMessage();
        }
    }

    public void additionalWork() {
        log.info("추가 작업 수행중, 현재 스레드 : {}", Thread.currentThread().getName());
    }
}
