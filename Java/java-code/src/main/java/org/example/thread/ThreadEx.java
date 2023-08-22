package org.example.thread;

import java.util.logging.Logger;

public class ThreadEx extends Thread {
    private String message;
    static final Logger logger = Logger.getGlobal();

    public ThreadEx(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        logger.info("message = "+ message);

//        for (var i = 0; i < 5; i++) {
//            //Thread를 상속받았기에 바로 method 호출이 된다.
//            System.out.println("ThreadEx : " + getName());
//        }
    }
}
