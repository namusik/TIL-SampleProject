package com.example.nio2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@Slf4j
public class NIO2Controller {
    @GetMapping("nio2")
    public String nio2(){
        StringBuilder response = new StringBuilder();

        try {
            // AsynchronousServerSocketChannel을 생성하고 포트 8081에 바인딩
            // 비차단 승인 작업을 허용
            AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress(8081));
            response.append("Server started on port 8081\n");

            // Accepting a connection asynchronously
            Future<AsynchronousSocketChannel> future = serverChannel.accept();
            AsynchronousSocketChannel clientChannel = future.get();
            response.append("Client connected\n");

            // Read data asynchronously
            ByteBuffer buffer = ByteBuffer.allocate(256);
            Future<Integer> readResult = clientChannel.read(buffer);
            readResult.get(); // Wait until read operation completes

            buffer.flip();
            response.append("Received : ").append(StandardCharsets.UTF_8.decode(buffer)).append("\n");

            // Write data asynchronously
            buffer.clear();
            buffer.put("Hello from server".getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            Future<Integer> writeResult = clientChannel.write(buffer);
            writeResult.get(); // Wait until write operation completes

            clientChannel.close();
        } catch (IOException | ExecutionException | InterruptedException e) {
            log.error(e.getMessage());
        }
        return response.toString();
    }
}
