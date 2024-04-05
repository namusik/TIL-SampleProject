package com.example.tcp.socketbinaydata;

import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Slf4j
public class BinaryClient {
    public void runClient(String ip, int port) {
        try {
            Socket socket = new Socket(ip, port);
            System.out.println("Connected to server ...");
            DataInputStream in = new DataInputStream(System.in);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            char type = 's'; // s for string
            String data = "This is a string of length 29";
            byte[] dataInBytes = data.getBytes(StandardCharsets.UTF_8);

            //Sending data in TLV format
            out.writeChar(type);
            out.writeInt(dataInBytes.length);
            out.write(dataInBytes);
        } catch (IOException e) {
            log.error("Failed to run client", e);
        }
    }
}
