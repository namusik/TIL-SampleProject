package org.example.thread;

public class WaitingState implements Runnable {
    public static Thread t1;

    public static void main(String[] args) {
        t1 = new Thread(new WaitingState());
        t1.setName("thread1");
        t1.start();
    }

    @Override
    public void run() {
        System.out.println("WaitingState run");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());

        Thread t2 = new Thread(new DemoWaitingStateRunnable());
        t2.setName("thread2");
        t2.start();
        System.out.println("t2.getState() = " + t2.getState());

        try {
            //t2를 start했지만, 아직 run()은 하기전.
            //join해야 run()된다.
            System.out.println("t2.join 시작");
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}

/**    Java에서 스레드를 생성하고 시작할 때, start() 메서드를 호출하면 스레드가 시작되고 run() 메서드가 실행됩니다.
 *
 *      그러나 스레드가 시작되면 run() 메서드가 바로 실행되지 않을 수 있습니다. 이는 Java의 스레드 스케줄링과 관련이 있습니다.

        스레드 스케줄링은 운영체제 및 JVM의 스레드 관리자에 의해 제어됩니다. 스레드는 실행되기를 기다리는 다른 스레드와 경쟁하며 실행 시간을 할당받습니다.

 이로 인해 t2.start()를 호출하더라도 t2 스레드가 즉시 실행되지 않을 수 있습니다.

        t2.start()를 호출하면 t2 스레드는 시작 대기 상태가 되며, 스레드 스케줄러에 의해 적절한 시간에 실행됩니다.

 t2 스레드가 실행되면 run() 메서드가 실행되고 "DemoWaitingStateRunnable 시작" 메시지가 출력됩니다.

 그 후 Thread.sleep(1000)을 호출하여 t2 스레드를 1초간 일시 중지시킵니다. 이후 t2 스레드가 다시 실행됩니다.

 t1 스레드의 t2.join() 호출은 t1 스레드가 t2 스레드가 종료될 때까지 대기해야 함을 의미합니다.

 그렇기 때문에 t2.join()을 호출한 후에 t2 스레드가 종료될 때까지 t1 스레드는 기다리게 됩니다. 이로 인해 t2 스레드의 실행이 완료되기 전까지 "t2.join 시작" 메시지가 출력되지 않습니다.
 **/
