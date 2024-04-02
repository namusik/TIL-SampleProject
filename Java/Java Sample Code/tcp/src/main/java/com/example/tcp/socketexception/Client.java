package com.example.tcp.socketexception;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("HELLO".getBytes());
            System.out.println("Writing to server..");
            //Here we are writing again.
            Thread.sleep(3000);
            outputStream.write("HI".getBytes());
            System.out.println("Writing to server again..");
            System.out.println("Closing client.");
            outputStream.close();
            socket.close();
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
