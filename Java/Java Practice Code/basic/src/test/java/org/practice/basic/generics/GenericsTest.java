package org.practice.basic.generics;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class GenericsTest {
    @Test
    void testGenerics() {
        List list = new LinkedList();
        list.add(1);
        Integer i = (Integer) list.iterator().next();

        log.info(String.valueOf(i));
    }

    @Test
    void diamondOperator() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        Integer next = list.iterator().next();

        log.info(String.valueOf(next));
    }

    @Test
    void typeParameter() {
        String[] stringArray = {"a", "b", "c"};
        List<String> stringList = fromArrayToList(stringArray);
        log.info("stringList: {}", stringList);

        Integer[] intArray = {1, 2, 3};
        List<Integer> intList = fromArrayToList(intArray);
        log.info("intList: {}", intList);

        Double[] doubleArray = {1.1, 2.2, 3.3};
        List<Double> doubleList = fromArrayToList(doubleArray);
        log.info("doubleList: {}", doubleList);
    }

    public <T> List<T> fromArrayToList(T[] array) {
        return Arrays.stream(array).collect(Collectors.toList());
    }


    @Test
    void multiTypeParameter() {
        String[] stringArray = {"a", "b", "c"};
        Integer[] intArray = {1, 2, 3};

        List<Object> combinedList = combineArraysToList(stringArray, intArray);

        log.info("combinedList: {}", combinedList);

        assertThat(combinedList.size()).isEqualTo(6);
    }

    public static <T, U> List<Object> combineArraysToList(T[] array1, U[] array2) {
        List<T> list1 = Arrays.stream(array1).toList();
        List<U> list2 = Arrays.stream(array2).toList();

        List<Object> combinedList = new ArrayList<>();
        combinedList.addAll(list1);
        combinedList.addAll(list2);

        return combinedList;
    }

    @Test
    void voidGeneric() {
        String[] stringArray = {"a", "b", "c"};
        Integer[] intArray = {1, 2, 3};

        printArrayElements(stringArray); // 출력: a b c
        printArrayElements(intArray);    // 출력: 1 2 3
    }

    public <T> void printArrayElements(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
