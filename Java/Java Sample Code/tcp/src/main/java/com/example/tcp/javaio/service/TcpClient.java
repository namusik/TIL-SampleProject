package com.example.tcp.javaio.service;

import com.example.tcp.javaio.model.RequestSendDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;

@Slf4j
@Service
public class TcpClient implements ApplicationRunner {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 8081;
    private static final String ID = "ADMIN";
    private static final String PASSWORD = "1234";
    private static final String AUTHORIZE = "AUTHORIZE";
    private static final String REPLY = "REPLY";
    private static final String DATA = "DATA";
    private static final String FAIL = "FAIL";

    @Override
    public void run(ApplicationArguments args) {
        newConnection();
    }

    public void newConnection() {
        try  {
            Socket socket = new Socket(SERVER_ADDRESS, PORT);
            log.info("<TCP CLIENT> 소켓 연결 성공 : {}", socket.getRemoteSocketAddress());
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            String payload = String.join(",", AUTHORIZE, ID, PASSWORD);
            sendTcp(payload, printWriter);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            
            String response = bufferedReader.readLine();
            log.info("<TCP CLIENT> RECEIVE DATA :: {}", response);
            String[] payloadArray = response.split(",");
            if (payloadArray[0].equals(REPLY) && payloadArray[1].equals(FAIL)) {
                socket.close();
            }
        } catch (Exception e) {
            log.error("tcp 통신 실패 ", e);
        }
    }

//    public String sendMessage(RequestSendDto requestSendDto) {
//        String payload = String.join(",", DATA, requestSendDto.getBody());
//        return sendTcp(payload, printWriter);
//    }

    public String sendTcp(String payload, PrintWriter printWriter) {
        if (printWriter != null) {
            log.info("<TCP CLIENT> SEND DATA :: {}", payload);
            printWriter.println(payload);
            return "success";
        } else {
            log.error("<TCP CLIENT> printWriter가 존재하지 않습니다.");
            return "fail";
        }
    }
}
