package org.practice.basic.javaIO;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class FileWriterEx {
    public FileWriterEx(String file) {
    }

    public static void main(String[] args) {
        try (java.io.FileWriter writer = new java.io.FileWriter("charOutput.txt")) {
            writer.write("Hello World");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        try (FileReader reader = new FileReader("charOutput.txt")) {
            int data;
            while ((data = reader.read()) != -1) {
                System.out.println((char) data);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
