package org.example.thread;

public class ThreadJoin2 extends Thread{

    @Override
    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException 발생");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadJoin2 threadJoin2 = new ThreadJoin2();
        threadJoin2.start();
        //interrupt 예약
        threadJoin2.interrupt();

        //threadJoin2 스레드가 종료할 때까지 main thread는 기다리겠다.
        threadJoin2.join();

        System.out.println("main thread 종료");
    }
}
