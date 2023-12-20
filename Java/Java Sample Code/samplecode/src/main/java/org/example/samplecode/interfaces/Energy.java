package org.example.samplecode.interfaces;

public interface Energy {
    String getName();

    default void hello() {
        System.out.println("energy 인터페이스");
    }
}
