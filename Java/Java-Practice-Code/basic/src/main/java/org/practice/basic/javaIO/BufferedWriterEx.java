package org.practice.basic.javaIO;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class BufferedWriterEx {
    public static void main(String[] args) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("buffered.txt"))) {
            writer.write("Hello World");
            writer.newLine();
            writer.write("Hello World2");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("buffered.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
