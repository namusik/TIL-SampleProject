package org.design.pattern.creational_patterns.singleton;

import java.io.Serializable;

public class EnhancedThreadSafeSingleton implements Serializable {
    private static final long serialVersionUID = 1L; // 명시적으로 선언하지 않으면 Java 컴파일러는 필드, 메서드, 생성자 등 클래스의 다양한 측면을 기반으로 자동으로 버전을 생성

    private static volatile EnhancedThreadSafeSingleton instance;

    private EnhancedThreadSafeSingleton() {
        // Preventing Reflection
        if (instance != null) { // 리플렉션을 통해 두 번째 인스턴스를 생성하려고 하면 'RuntimeException'이 발생
            throw new RuntimeException("Use getInstance() method to create the singleton instance.");
        }
        System.out.println("EnhancedThreadSafeSingleton instance created.");
    }

    public static EnhancedThreadSafeSingleton getInstance() { // 이중 확인 잠금
        if (instance == null) { // First check (no locking) 인스턴스가 이미 초기화된 경우 동기화 오버헤드를 방지
            synchronized (EnhancedThreadSafeSingleton.class) { // 하나의 스레드만 인스턴스를 초기화할 수 있도록 보장
                if (instance == null) { // Second check (with locking) 인스턴스를 생성하기 전에 인스턴스가 여전히 'null'인지 다시 확인
                    instance = new EnhancedThreadSafeSingleton();
                }
            }
        }
        return instance;
    }

    protected Object readResolve() { // 역직렬화가 새 싱글톤 인스턴스를 생성하는 대신 기존 싱글톤 인스턴스를 반환하도록
        return getInstance();
    }

    public void showMessage() {
        System.out.println("Hello from EnhancedThreadSafeSingleton! Instance HashCode: " + this.hashCode());
    }
}
