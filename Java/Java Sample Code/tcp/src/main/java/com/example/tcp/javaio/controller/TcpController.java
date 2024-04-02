package com.example.tcp.javaio.controller;

import com.example.tcp.javaio.service.TcpClient;
import com.example.tcp.javaio.model.RequestSendDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/io")
public class TcpController {
    private final TcpClient tcpClient;
    @PostMapping("/send")
    public String sendMessage(@RequestBody RequestSendDto requestSendDto) {
        log.info("메시지 내용 :: {}", requestSendDto);
        return null;
//        return tcpClient.sendMessage(requestSendDto);
    }

    @PostMapping("/new")
    public String newConnection() {
        log.info("새로운 socket 연결 요청");
        tcpClient.newConnection();
        return "success";
    }
}
