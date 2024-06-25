package org.practice.basic.dataType.array;

import java.util.Arrays;
import java.util.stream.Stream;

public class ArrayExample {
    public static void main(String[] args) {
        String[] anArray = new String[] {"Milk", "Tomato", "Chips"};
        Stream<String> aStream = Arrays.stream(anArray);

        System.out.println("aStream = " + aStream);
    }
}
