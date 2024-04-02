package com.example.tcp.socketsample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class EchoClientTest {
    private EchoClient client;
    @BeforeEach
    public void setup() throws IOException {
        client = new EchoClient();
        client.startConnection("127.0.0.1", 4444);
    }

    @AfterEach
    public void tearDown() throws IOException {
        client.stopConnection();
    }

    @Test
    public void givenClient_whenServerEchosMessage_thenCorrect() throws IOException {
        String resp1 = client.sendMessage("hello");
        System.out.println("resp1 = " + resp1);
        String resp2 = client.sendMessage("world");
        System.out.println("resp2 = " + resp2);
        String resp3 = client.sendMessage("!");
        System.out.println("resp3 = " + resp3);
        String resp4 = client.sendMessage(".");
        System.out.println("resp4 = " + resp4);

        assertEquals("hello", resp1);
        assertEquals("world", resp2);
        assertEquals("!", resp3);
        assertEquals("good bye", resp4);
    }
}