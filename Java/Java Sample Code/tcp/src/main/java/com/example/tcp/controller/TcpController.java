package com.example.tcp.controller;

import com.example.tcp.model.RequestSendDto;
import com.example.tcp.service.TcpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TcpController {
    private final TcpClient tcpClient;
    @PostMapping("/send")
    public void sendMessage(@ModelAttribute RequestSendDto requestSendDto) {
        log.info("메시지 내용 :: {}", requestSendDto);
        tcpClient.sendMessage(requestSendDto);

    }
}
