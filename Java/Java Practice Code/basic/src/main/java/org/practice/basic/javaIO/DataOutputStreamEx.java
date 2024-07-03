package org.practice.basic.javaIO;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class DataOutputStreamEx {
    public static void main(String[] args) {
        try (DataOutputStream dataOut = new DataOutputStream(new FileOutputStream("data.dat"))) {
            dataOut.writeInt(123);
            dataOut.writeBoolean(true);
            dataOut.writeChar('a');
            dataOut.writeFloat(1.234f);
            dataOut.writeDouble(1.234d);
            dataOut.writeUTF("Hello World");
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        try(DataInputStream dataIn = new DataInputStream(new FileInputStream("data.dat"))) {
            int i = dataIn.readInt();
            boolean b = dataIn.readBoolean();
            char c = dataIn.readChar();
            float v = dataIn.readFloat();
            double v1 = dataIn.readDouble();
            String s = dataIn.readUTF();

            System.out.println("s = " + s);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
