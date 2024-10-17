package org.design.pattern.creational_patterns.singleton;

public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance; // ThreadSafeSingleton의 유일한 인스턴스를 저장

    private ThreadSafeSingleton() {
        try {
            Thread.sleep(100); // 시간이 많이 걸리는 초기화 프로세스를 시뮬레이션하여 테스트 중에 스레드 동기화 효과를 더 쉽게 관찰할 수 있도록
        } catch (InterruptedException e) {
            System.out.println("exception = " + e);
        }
        System.out.println("ThreadSafeSingleton instantiated");
    }

    public static synchronized ThreadSafeSingleton getInstance() { // synchronized 한 번에 하나의 스레드만 이 메서드를 실행할 수 있도록 하여 여러 스레드가 동시에 별도의 인스턴스를 생성하는 것을 방지
        if (instance == null) {
            System.out.println(Thread.currentThread().getName() + " is creating the Singleton instance.");
            instance = new ThreadSafeSingleton();
        } else {
            System.out.println(Thread.currentThread().getName() + " accessed the existing Singleton instance.");
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from ThreadSafeSingleton! Instance HashCode: " + this.hashCode());
    }
}
