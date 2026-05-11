package org.practice.basic.javaIO;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

@Slf4j
public class JavaNIO2 {
    public static void main(String[] args) {
        Path path = Paths.get("/Users/ioi01-ws_nam/Documents/GitHub/TIL-SampleProject/Java/Java Practice Code/basic/byteOutPut.txt");

        String fileName = path.getFileName().toString();

        log.info(fileName);

        try {
            Files.writeString(path, "\nadd Lines4", StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> lines = null;
        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.info(String.join("\n", lines));

        try {
            Files.walkFileTree(Paths.get("/Users/ioi01-ws_nam/Documents/GitHub/TIL-SampleProject/Java/Java Practice Code/basic"), new SimpleFileVisitor<>() {
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    log.info("visitedFile : {}", file);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
