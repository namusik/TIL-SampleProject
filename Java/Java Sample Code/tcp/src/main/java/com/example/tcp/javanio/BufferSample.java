package com.example.tcp.javanio;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Slf4j
public class BufferSample {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(256);

        for (int i = 0; i < 10; i++) {
            buffer.put((byte) i);
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            log.info("buffer :: {}", buffer.get());
        }

        buffer.clear();
    }
}
