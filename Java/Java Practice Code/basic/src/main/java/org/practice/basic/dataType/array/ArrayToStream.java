package org.practice.basic.dataType.array;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayToStream {

    public static void main(String[] args) {

        String[] anArray = new String[] {"Milk", "Tomato", "Chips"};
        Stream<String> aStream = Arrays.stream(anArray);

        aStream.forEach(System.out::println);


        int[] ints = new int[] {1,2,3,4};

        IntStream stream = Arrays.stream(ints);

        System.out.println("stream = " + stream);

    }
}
