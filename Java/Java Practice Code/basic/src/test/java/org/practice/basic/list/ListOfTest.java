package org.practice.basic.list;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ListOfTest {
    @Test
    void list() {
        List<String> list1 = new ArrayList<>();
        list1.add("aaa");
        System.out.println("list1 = " + list1);


        List<String> list3 = Arrays.asList("bbb");
//        list3.add("33");
        System.out.println("list3 = " + list3);
    }

    @Test
    @DisplayName("List.of()는 add 불가능")
    void addToListOf() {
        List<String> listOf = List.of("aaa", "bbb");
        System.out.println("listOf = " + listOf);

        Assertions.assertThatThrownBy(() -> listOf.add("ccc"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("List.of()는 remove 불가능")
    void removeFromListOf() {
        List<String> listOf = List.of("aaa", "bbb");
        System.out.println("listOf = " + listOf);

        Assertions.assertThatThrownBy(() -> listOf.remove("aaa"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("List.of()는 set 불가능")
    void setListOf() {
        List<String> listOf = List.of("aaa", "bbb");
        System.out.println("listOf = " + listOf);

        Assertions.assertThatThrownBy(() -> listOf.set(0, "ccc"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

}