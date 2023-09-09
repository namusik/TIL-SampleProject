package org.example;

import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.List;
import java.util.UUID;

public class UUIDTest {
    @Test
    void test() {
        var objects = List.of();
        for (int i = 0; i < 41; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println("uuid = " + uuid.replace("-", ""));
            objects.add(uuid);
        }


    }

}
