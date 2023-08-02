package org.example.finalEx;

import java.util.Arrays;
import java.util.List;

public class EffectivelyFinalEx {
    public static void main(String[] args) {
        /**
         * lambda와 effectively final
         * 값이 변경되거나 참조가 바뀌는 변수는 'effecitvely final'이 아니다.
         * 이러한 변수를 lambda 내부에서 사용하면 컴파일 오류가 발생한다.
         * Variable used in lambda expression should be final or effectively final
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int i = 3;
//        i = 4;
        list.removeIf(v -> v == i+1);

        /**
         * 참조 변수의 경우도 마찬가지로 참조가 바뀌면 'effectively final'이 아니므로 lambda에서 사용이 불가능하다.
         */
        Dog dog = new Dog("seoul");
        dog.setAge(13);
//        dog = new Dog("pusan");
        list.removeIf(v -> v == dog.getAge());
    }
}
