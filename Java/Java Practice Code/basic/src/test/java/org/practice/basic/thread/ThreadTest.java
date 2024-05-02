package org.practice.basic.thread;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ThreadTest {
    @Test
    void threadEx() throws InterruptedException {
        // Thread 상속법
        var threadEx = new ThreadEx("threadEx");

        threadEx.start();
        threadEx.join();
    }

    @Test
    void threadException() throws InterruptedException {
        // Thread 상속법
        var threadEx = new ThreadEx("threadEx");

        threadEx.start();
        threadEx.join();
        //start()는 한 쓰레드당 한번 호출 가능하다.
        Assertions.assertThatThrownBy(() -> threadEx.start())
                .isInstanceOf(IllegalThreadStateException.class);
    }

    @Test
    void runnableImpl() throws InterruptedException {
        // Runnable 구현법
        //Runnable 구현체의 instance를 만들어서 Thread의 생성자 매개변수로 넘긴다.
        var r = new RunnableImpl("runnableImpl");
        var thread = new Thread(r);

        thread.start();
        thread.join();
    }

    @Test
    void lambdaRunnable() throws InterruptedException {
        //Runnable은 함수형 인터페이스니까 람다표현식으로 구현한다.
        var thread = new Thread(() -> System.out.println("runnable"));

        thread.start();
        thread.join();
    }
}