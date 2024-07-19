package org.practice.basic.list;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ArrayListTest {
    @Test
    void list() {
        List<String> list1 = new ArrayList<>();
        list1.add("aaa");
        System.out.println("list1 = " + list1);
    }
}