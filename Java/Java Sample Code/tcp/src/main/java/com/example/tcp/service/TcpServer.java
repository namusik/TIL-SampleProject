package com.example.tcp.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

@Slf4j
@Service
public class TcpServer {
    private static final int PORT = 8081;
    private static final int TIMEOUT = 10000;

    @PostConstruct
    // 객체의 초기화가 완료된 후 자동으로 호출. TCP 서버를 시작
    public void startServer() {
        new Thread(() -> {
            // 서버 소켓을 별도의 스레드에서 실행.
            // 서버가 클라이언트의 연결을 비동기적으로 수신할 수 있도록. 서버의 main thread가 blocking 되지 않고 다른 작업을 처리할 수 있도록
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {

                // 클라이언트 연결 요청 10초 기다림
                serverSocket.setSoTimeout(TIMEOUT);

                log.info("Server is listening on port {}", PORT);
                log.info("Waiting for a connection for 10 seconds...");

                try {
                    Socket socket = serverSocket.accept();

                    log.info("소켓 연결 성공 : {}", socket.getRemoteSocketAddress());

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String readLine = bufferedReader.readLine();

                    log.info("readLine :: {}", readLine);
                } catch (SocketTimeoutException e) {
                    log.error("SocketTimeoutException 발생", e);
                }
            } catch (IOException e) {
                log.error("서버 에러 발생", e);
                throw new RuntimeException(e);
            }
        }).start();
    }
}
