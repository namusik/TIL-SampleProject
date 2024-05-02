package org.practice.basic.interfaces;

public interface Energy {
    String getName();

    default void hello() {
        System.out.println("energy 인터페이스");
    }
}
