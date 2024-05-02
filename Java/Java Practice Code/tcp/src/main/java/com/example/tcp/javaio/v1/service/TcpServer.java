package com.example.tcp.javaio.v1.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

@Slf4j
@Service
public class TcpServer {

    private static final int PORT = 8081;
    private static final int TIMEOUT = 10000;
    private static final String ID = "ADMIN";
    private static final String PASSWORD = "1234";

    private PrintWriter printWriter;

    @PostConstruct
    // 객체의 초기화가 완료된 후 자동으로 호출. TCP 서버를 시작
    public void startServer() {
        new Thread(() -> {
            // 서버 소켓을 별도의 스레드에서 실행.
            log.info("<TCP SERVER> thread name : {}", Thread.currentThread().getName());
            // 서버가 클라이언트의 연결을 비동기적으로 수신할 수 있도록. 서버의 main thread가 blocking 되지 않고 다른 작업을 처리할 수 있도록
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {

                // 클라이언트 연결 요청 10초 기다림
                serverSocket.setSoTimeout(TIMEOUT);

                log.info("<TCP SERVER> Server is listening on port {}", PORT);
                log.info("<TCP SERVER> Waiting for a connection for 10 seconds...");

                try {
                    Socket socket = serverSocket.accept();

                    log.info("<TCP SERVER> 소켓 연결 성공 : {}", socket.getRemoteSocketAddress());

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    printWriter = new PrintWriter(socket.getOutputStream(), true);
                    String payload;
                    while ((payload = bufferedReader.readLine()) != null) {
                        log.info("<TCP SERVER> RECEIVE DATA :: {}", payload);

                        String[] payloadArray = payload.split(",");

                        switch (payloadArray[0]) {
                            case "AUTHORIZE" -> {
                                if (payloadArray.length == 3 && payloadArray[1].equals(ID) && payloadArray[2].equals(PASSWORD)) {
                                    log.info("<TCP SERVER> AUTHORIZE SUCCESS");
                                    String reply = String.join(",", "REPLY", "SUCCESS");
                                    sendTcp(reply);
                                } else {
                                    log.info("<TCP SERVER> AUTHORIZE FAIL");
                                    String reply = String.join(",", "REPLY", "FAIL");
                                    sendTcp(reply);
                                    socket.close();
                                }
                            }
                            case "DATA" -> {
                                if (payloadArray.length == 2) {
                                    log.info("<TCP SERVER> RECEIVE DATA :: {}", payloadArray[1]);
                                } else {
                                    log.info("<TCP SERVER> 데이터 형식 오류 :: {}", payloadArray.length);
                                }
                            }
                        }
                    }
                } catch (SocketTimeoutException e) {
                    log.error("SocketTimeoutException 발생", e);
                }
            } catch (IOException e) {
                log.error("서버 에러 발생", e);
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void sendTcp(String reply) {
        if (printWriter != null) {
            log.info("<TCP SERVER> SEND DATA :: {}", reply);
            printWriter.println(reply);
        } else {
            log.error("printWriter가 존재하지 않습니다.");
        }
    }
}
