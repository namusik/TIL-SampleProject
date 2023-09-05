package org.example.thread;

public class DemoWaitingStateRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("DemoWaitingStateRunnable 시작");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("e 발생= " + e);
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        //join()으로 호출한 t2가 아직 실행중이기 때문에 여기서 t1상태는 WAITING이다.
        System.out.println(WaitingState.t1.getState());
    }
}
