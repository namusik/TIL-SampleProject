package org.example.thread;

/**
 * t1을 start 했지만, Thread.sleep(5000);에  빠져버렸다
 * 그래서 1000ms가 지난 후 상태를 확인해보면 아직 sleep 이기 때문에
 *  TIMED_WAITING이다.
 */

public class TimedWaitingState {
    public static void main(String[] args) throws InterruptedException {
        var t1 = new Thread(new DemoTimeWaitingRunnable());
        System.out.println("1 : "+t1.getState());
        t1.start();
        System.out.println("2 : "+t1.getState());

        Thread.sleep(1000);
        System.out.println("3 : "+t1.getState());
        Thread.sleep(4000);
    }

    static class DemoTimeWaitingRunnable implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}


