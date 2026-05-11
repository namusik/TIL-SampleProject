package org.practice.basic.thread;

import org.junit.jupiter.api.Test;

public class SingleMultiThreadTest {
    @Test
    void singleThreadTest() {
        var startTime = System.currentTimeMillis();

        for (var i = 0; i < 300; i++) {
            System.out.printf("%s", new String("-"));
        }
        System.out.println("소요시간1:" + (System.currentTimeMillis() - startTime));

        for (var i = 0; i < 300; i++) {
            System.out.printf("%s", new String("|"));
        }
        System.out.println("소요시간2:" + (System.currentTimeMillis() - startTime));
    }

    static long startTime = 0;

    @Test
    void multiThreadTest() {
        var runnable = new RunnableImpl();
        var thread = new Thread(runnable);
        thread.start();
        startTime = System.currentTimeMillis();

        for (var i = 0; i < 300; i++) {
            System.out.printf("%s", new String("-"));
        }
        System.out.println("소요시간1:" + (System.currentTimeMillis() - startTime));
    }

    class RunnableImpl implements Runnable {
        @Override
        public void run() {
            for (var i = 0; i < 300; i++) {
                System.out.printf("%s", new String("|"));
            }
            System.out.println("소요시간2:" + (System.currentTimeMillis() - startTime));
        }
    }

}
