package org.example.thread;

public class WaitingState implements Runnable {
    public static Thread t1;

    public WaitingState() {
    }

    public static void main(String[] args) {
        t1 = new Thread(new WaitingState());
        t1.start();
    }

    @Override
    public void run() {
        Thread t2 = new Thread(new DemoWaitingStateRunnable());
        t2.start();

        try {
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
