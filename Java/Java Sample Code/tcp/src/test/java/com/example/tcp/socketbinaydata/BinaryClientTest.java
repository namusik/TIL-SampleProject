package com.example.tcp.socketbinaydata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryClientTest {
    @Test
    void sendBinaryData() {
        BinaryClient binaryClient = new BinaryClient();
        binaryClient.runClient("localhost", 8888);
    }

}