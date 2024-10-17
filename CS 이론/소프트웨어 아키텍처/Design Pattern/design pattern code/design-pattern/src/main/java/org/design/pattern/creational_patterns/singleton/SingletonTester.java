package org.design.pattern.creational_patterns.singleton;

public class SingletonTester implements Runnable {
    @Override
    public void run() {
        ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance(); // 각 스레드는 ThreadSafeSingleton.getInstance()를 호출하여 싱글톤 인스턴스를 얻는다.aa
        singleton.showMessage();
    }
}
