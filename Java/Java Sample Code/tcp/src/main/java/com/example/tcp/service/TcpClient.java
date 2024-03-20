package com.example.tcp.service;

import com.example.tcp.model.RequestSendDto;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
@Service
public class TcpClient{

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 8081;

    private PrintWriter printWriter;
    private static final String ID = "ADMIN";
    private static final String PASSWORD = "1234";

    @PostConstruct
    public void connect() {

        try (Socket socket = new Socket(SERVER_ADDRESS, PORT)) {
            printWriter = new PrintWriter(socket.getOutputStream(), true);


            printWriter.println("CONNECT");
            printWriter.println(ID);
            printWriter.println(PASSWORD);

            log.info("message sent :: {}");

        } catch (Exception e) {
            log.error("tcp 통신 실패 ", e);
        }
    }

    public void sendMessage(RequestSendDto requestSendDto) {
        printWriter.println(requestSendDto.getBody());

    }
}
