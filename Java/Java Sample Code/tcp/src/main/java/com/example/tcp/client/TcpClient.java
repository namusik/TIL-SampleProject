package com.example.tcp.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Slf4j
@Component
public class TcpClient implements ApplicationRunner {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 8080;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        try (Socket socket = new Socket(SERVER_ADDRESS, PORT)) {

        } catch (Exception e) {

        }
    }
}
