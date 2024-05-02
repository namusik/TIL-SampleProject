package com.example.tcp.baeldung.socketbinaydata;

import lombok.extern.slf4j.Slf4j;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Slf4j
public class BinaryServer {
    public void runServer(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("serverSocket = " + serverSocket);
            Socket socket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            char dataType = dataInputStream.readChar();
            System.out.println("dataType = " + dataType);
            int length = dataInputStream.readInt();
            System.out.println("length = " + length);

            if (dataType == 's') {
                // 데이터를 저장할 바이트 배열을 초기화
                // 배열의 크기는 데이터의 길이(length)와 동일
                // 데이터의 길이가 정해져있기 때문에, 메모리 낭비를 방지하기 위해 길이를 지정해준다.
                byte[] messageByte = new byte[length];
                //  데이터 읽기 작업의 완료 여부를 추적하는 플래그
                boolean end = false;
                // 읽은 데이터를 문자열로 변환하여 저장하기 위한 StringBuilder 객체를 초기화
                // 초기 용량을 지정하면 빈번한 메모리 재할당을 방지하여 성능을 개선
                StringBuilder dataString = new StringBuilder(length);
                // 현재까지 읽은 바이트 수를 추적하는 변수
                int totalBytesRead = 0;
                while (!end) { // 모든 데이터를 읽을 때까지 루프를 반복
                    // 배열의 크기만큼 바이너리를 읽는다.
                    // 실제로 읽은 바이트 수
                    int currentBytesRead = dataInputStream.read(messageByte);
                    log.info("currentByRead :: {}", currentBytesRead);
                    // 총 읽은 바이트수에 위에서 읽은 바이트 수 추가
                    totalBytesRead += currentBytesRead;

                    if (totalBytesRead <= length) { // 총 읽은 바이트 수가 읽어야 될 바이트 수보다 같거나 작을 때
                        // 읽어들인 모든 바이트를 바이트 배열을 문자열로 변환해서 dataString에 추가
                        // 1. 문자열로 변환할 바이트 배열
                        // 2. 배열에서 문자열로 변환을 시작할 인덱스. 0이면 배열의 시작부터 변환을 시작.
                        // 3. 시작인덱스로부터 몇 바이트를 문자로 변환할지.
                        // 4. 문자 인코딩
                        // append()를 통해 StringBuilder 객체에 문자열을 추가. 기존의 문자열 빌더 뒤에 추가됨
                        dataString.append(new String(messageByte, 0, currentBytesRead, StandardCharsets.UTF_8));
                        log.info("Message = {}", dataString);
                    } else { // 예상한 길이보다 더 많은 데이터를 읽어들인 경우
                        // (totalBytesRead - currentBytesRead) :: 이번 read 호출 전까지 실제로 읽은 데이터
                        // length에서 위를 빼면 읽어야 할 남은 데이터 길이가 된다.
                        log.info("더 많은 데이터를 읽음");
                        dataString.append(new String(messageByte,0,length - (totalBytesRead - currentBytesRead),StandardCharsets.UTF_8));
                    }
                    if (dataString.length() >= length) {
                        end = true;
                    }
                }
                log.info("binary data :: {}", messageByte);
                log.info("Read {} bytes of message from client. Message = {}", length, dataString);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        BinaryServer binaryServer = new BinaryServer();
        binaryServer.runServer(7878);
    }
}
