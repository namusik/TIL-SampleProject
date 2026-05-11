package org.example;

import java.util.function.Supplier;

public class LambdaEx {

    /**
     * static 혹은 instance 변수를 사용하는 경우
     *
     */
    int start = 0; //instance 변수
    static int end = 0; //static 변수

    Supplier<Integer> aa() {
        return () -> start++;
    }

    Supplier<Integer> bb() {
        return () -> end++;
    }

    /**
     * lambda가 지역변수를 사용할 때, final 혹은 effectively final이어야 한다.
     */
    Supplier<Integer> increment(int i) {
        return () -> i;
//        return () -> i++;
    }

    /**
     * 지역 변수를 사용할 때 값을 복사하는 이유
     * 만약 값을 복사하지 않는다면, 람다 스레드가 value를 출력하기 전에 value가 속해있는 스레드가 끝나버릴 수 있다.
     * 그러면 value는 사라지고 람다가 참조할 변수는 없어진다.
     */
    public void threadTest() {
        int value = 0;
        new Thread(() -> {
            try {
                Thread.sleep(100000);
                System.out.println(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
