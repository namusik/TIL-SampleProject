package org.practice.basic.thread;

import org.junit.jupiter.api.Test;

public class TreadCallStackTest {
    @Test
    void test() {
        var threadEx2 = new ThreadEx2();

        //stackTrace를 찍어보면, 최상단이 run() 이다.
        //test()가 있는 call stack과는 다른 쓰레드의 call stack이기 때문에.
        threadEx2.start();
    }

    @Test
    void test2() {
        var threadEx2 = new ThreadEx2();
        //start()가 아닌 run()메서드 자체를 호출해서 test2()와 같은 call stack에 있다.
        threadEx2.run();
    }

    class ThreadEx2 extends Thread {
        public void run() {
            throwException();
        }

        private void throwException() {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }





}
