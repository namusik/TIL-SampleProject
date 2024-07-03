package org.practice.basic.javaIO;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class JavaNIO2 {
    public static void main(String[] args) {
        Path path = Paths.get("byteOutput.txt");

        boolean exists = Files.exists(path);

        log.info("exists: {}", exists);

        boolean b = Files.notExists(path);

        log.info("not exists: {}", b);
    }
}
