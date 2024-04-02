package com.example.tcp.socketsample;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GreetClientTest {
    @Test
    public void givenGreetingClient_whenServerRespondsWhenStarted_thenCorrect() throws IOException {
        GreetClient client = new GreetClient();
        client.startConnection("localhost", 6666);
        String response = client.sendMessage("hello server");
        System.out.println("response = " + response);
        assertEquals("hello client", response);
    }
}