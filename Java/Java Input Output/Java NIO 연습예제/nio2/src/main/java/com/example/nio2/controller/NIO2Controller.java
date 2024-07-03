package com.example.nio2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;

@RestController
@Slf4j
public class NIO2Controller {
    @GetMapping("nio2")
    public String nio2(){
        StringBuilder response = new StringBuilder();

        try {
            // AsynchronousServerSocketChannel을 생성하고 포트 8081에 바인딩
            // 비차단 승인 작업을 허용
            AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(8081));
            response.append("Server started on port 8081\n");

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
