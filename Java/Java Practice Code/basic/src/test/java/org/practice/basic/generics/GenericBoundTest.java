package org.practice.basic.generics;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class GenericBoundTest {

    @Test
    void testGenerics() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3, 4.4);


        System.out.println(sumOfList(intList)); // 출력: 15.0
        System.out.println(sumOfList(doubleList)); // 출력: 11.0

    }

    @Test
    void failBound() {
        List<String> stringList = Arrays.asList("a", "b", "c");

        // compile 부터 실패
//        System.out.println(sumOfList(stringList));
    }

    public <T extends Number> double sumOfList(List<T> list) {
        double sum = 0;
        for (T number : list) {
            sum += number.doubleValue();
        }
        return sum;
    }

    @Test
    void lowerBoundTest() {
        List<Number> numberList = new ArrayList<>();
        addNumbers(numberList);
    }

    // 하한 경계를 사용하여 Integer와 그 슈퍼클래스만 허용
    public void addNumbers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
//        list.add(3.0); // 컴파일 오류: 3.0은 Double 타입
    }
}
