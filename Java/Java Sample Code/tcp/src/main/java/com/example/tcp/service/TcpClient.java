package com.example.tcp.service;

import com.example.tcp.model.RequestSendDto;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
@Service
public class TcpClient implements ApplicationRunner {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 8081;

    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private static final String ID = "ADMIN";
    private static final String PASSWORD = "1234";

    @Override
    public void run(ApplicationArguments args) {

        try  {
            Socket socket = new Socket(SERVER_ADDRESS, PORT);
            printWriter = new PrintWriter(socket.getOutputStream(), true);

            String payload = String.join(",", "CONNECT", ID, PASSWORD);
            sendTcp(payload);

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = bufferedReader.readLine();
            log.info("server response :: {}", response);
            if (response.split(",")[1].equals("로그인실패")) {
                socket.close();
            }
        } catch (Exception e) {
            log.error("tcp 통신 실패 ", e);
        }
    }

    public void sendMessage(RequestSendDto requestSendDto) {
        String payload = String.join(",", "DATA", requestSendDto.getBody());
        sendTcp(payload);
    }

    public void sendTcp(String payload) {
        if (printWriter != null) {
            log.info("payload :: {}", payload);
            printWriter.println(payload);
        } else {
            log.error("printWriter가 존재하지 않습니다.");
        }
    }
}
