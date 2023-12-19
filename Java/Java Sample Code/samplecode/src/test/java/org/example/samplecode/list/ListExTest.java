package org.example.samplecode.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ListExTest {
    @Test
    void list() {
        List<String> list1 = new ArrayList<>();
        list1.add("aaa");
        System.out.println("list1 = " + list1);

        List<String> list2 = List.of();
//        list2.add("dd");
        System.out.println("list2 = " + list2);


        List<String> list3 = Arrays.asList("bbb");
//        list3.add("33");
        System.out.println("list3 = " + list3);
    }

}