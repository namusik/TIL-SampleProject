package org.design.pattern.creational_patterns.singleton;

import org.junit.jupiter.api.Test;

class ThreadSafeSingletonTest {

    @Test
    void testSingleton() {

        int number = 5;

        Thread[] threads = new Thread[number];

        for (int i = 0; i < number; i++) {
            threads[i] = new Thread(new SingletonTester(), "Thread-" + i); // 싱글톤에 동시에 액세스를 시도하는 여러 스레드(Thread-1 ~ Thread-5)를 생성
            threads[i].start();
        }

        // 모든 스레드가 완료될 때까지 대기
        for (int i = 0; i < number; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("e = " + e);
            }
        }
        System.out.println("모든 스레드가 완료되었습니다.");
    }

    /**
     * Thread-0 is creating the Singleton instance.
     * ThreadSafeSingleton instantiated
     * Thread-1 accessed the existing Singleton instance.
     * Hello from ThreadSafeSingleton! Instance HashCode: 566134584
     * Hello from ThreadSafeSingleton! Instance HashCode: 566134584
     * Thread-2 accessed the existing Singleton instance.
     * Hello from ThreadSafeSingleton! Instance HashCode: 566134584
     * Thread-3 accessed the existing Singleton instance.
     * Hello from ThreadSafeSingleton! Instance HashCode: 566134584
     * Thread-4 accessed the existing Singleton instance.
     * Hello from ThreadSafeSingleton! Instance HashCode: 566134584
     * 모든 스레드가 완료되었습니다.
     */

    /**
     * 모든 스레드가 동일한 hashCode를 출력하여 동일한 Singleton 인스턴스를 참조하고 있음을 확인 가능
     * 첫 번째 스레드(Thread-1)만 인스턴스를 생성하고, 나머지 스레드들은 기존 인스턴스에 접근함을 로그로 확인
     */


}