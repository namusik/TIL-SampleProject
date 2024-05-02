package org.practice.basic.thread;

public class TerminatedState implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        var t1 = new Thread(new TerminatedState());
        t1.start();

        Thread.sleep(1000);
        System.out.println(t1.getState());
        System.out.println(t1.isAlive());
    }

    @Override
    public void run() {

    }
}
