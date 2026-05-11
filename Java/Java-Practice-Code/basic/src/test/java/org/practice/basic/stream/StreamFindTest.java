package org.practice.basic.stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StreamFindTest {
    List<Integer> list;

    Map<Integer, Integer> map;
    @BeforeEach
    void init() {
        list = Arrays.asList(1, 2, 3, 4, 5);
        map = Map.of(1, 1, 2, 2, 3, 3, 4, 4);
    }

    @Test
    void findAnyList() {
        var any = list.stream().findAny().get();
        System.out.println("any = " + any);
    }

    @Test
    void findFirstList() {
        var i = list.stream().findFirst().get();
        System.out.println("i = " + i);
    }
}
