package com.example.tcp.socketbinaydata;

import com.example.tcp.baeldung.socketbinaydata.BinaryClient;
import org.junit.jupiter.api.Test;

class BinaryClientTest {
    @Test
    void sendBinaryData() {
        BinaryClient binaryClient = new BinaryClient();
        binaryClient.runClient("localhost", 7878);
    }

}