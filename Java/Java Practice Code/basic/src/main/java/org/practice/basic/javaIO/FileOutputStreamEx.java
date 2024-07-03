package org.practice.basic.javaIO;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
public class FileOutputStreamEx {
    public static void main(String[] args) {
        /**
         * FileOutputStream을 사용해서 파일 쓰기
         */
        try (java.io.FileOutputStream fos = new java.io.FileOutputStream("byteOutPut.txt")) {
            String data = "Hello World!";
            fos.write(data.getBytes());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        /**
         * FileInputStream을 사용해서 파일 읽기
         */
        try (FileInputStream fis = new FileInputStream("byteOutPut.txt")) {
            int byteData;
            while ((byteData = fis.read()) != -1) {
                System.out.print((char) byteData);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
