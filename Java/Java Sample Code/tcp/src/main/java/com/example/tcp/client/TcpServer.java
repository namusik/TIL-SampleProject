package com.example.tcp.client;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
@Service
public class TcpServer {
    private static final int PORT = 8080;

    @PostConstruct
    // 객체의 초기화가 완료된 후 자동으로 호출. TCP 서버를 시작
    public void startServer() {
        new Thread(() -> {
            // 서버 소켓을 별도의 스레드에서 실행.
            // 서버가 클라이언트의 연결을 비동기적으로 수신할 수 있도록. 서버의 main thread가 blocking 되지 않고 다른 작업을 처리할 수 있도록
            try {
                ServerSocket serverSocket = new ServerSocket(PORT);
                log.info("TCP Server start : {}", PORT);

                while (true) {
                    Socket clientSocket = serverSocket.accept();

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

    }
}
