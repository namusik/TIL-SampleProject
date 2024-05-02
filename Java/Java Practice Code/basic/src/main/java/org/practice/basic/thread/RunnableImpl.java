package org.practice.basic.thread;

import java.util.logging.Logger;

public class RunnableImpl implements Runnable{
    private String message;
    static final Logger logger = Logger.getGlobal();

    public RunnableImpl(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        logger.info("message = "+ message);
//        for (var i = 0; i < 5; i++) {
//            //Thread.currentThread() : 현재 실행중인 Thread를 반환
//            //static method라서 바로 쓸 수 있다.
//            System.out.println("RunnableImpl : "+Thread.currentThread().getName());
//        }
    }
}
