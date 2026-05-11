package org.practice.basic.list;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class SingletonListTest {

    String element = "a";

    @Test
    @DisplayName("singletonList는 add 불가능")
    void addToListOf() {
        List<String> singletonList = Collections.singletonList(element);
        System.out.println("singletonList = " + singletonList);

        Assertions.assertThatThrownBy(() -> singletonList.add("b"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("singletonList는 remove 불가능")
    void removeFromListOf() {
        List<String> singletonList = Collections.singletonList(element);
        System.out.println("singletonList = " + singletonList);

        Assertions.assertThatThrownBy(() -> singletonList.remove("a"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("singletonList는 set 불가능")
    void setListOf() {
        List<String> singletonList = Collections.singletonList(element);
        System.out.println("singletonList = " + singletonList);

        Assertions.assertThatThrownBy(() -> singletonList.set(0, "b"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

}