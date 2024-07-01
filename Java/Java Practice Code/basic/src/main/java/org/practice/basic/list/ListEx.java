package org.practice.basic.list;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ListEx {

    public static void main(String[] args) {

        String[] array = {"foo", "bar"};

        List<String> list = Arrays.asList(array);

        log.info(list.toString());

        array[0] = "goo";

        log.info(list.toString());

//        list.add("baz");

        List<String> list2 = Stream.of("foo", "bar")
                .toList();

        log.info(list2.toString());

        List<String> list3 = List.of("foo", "bar", "baz");

        log.info(list3.toString());

        list3.add("aaa");

    }

    List<String> list1 = new ArrayList<>();

    List<String> list2 = List.of();

    List<String> list3 = Arrays.asList();
}
