package org.practice.basic.interfaces;

public interface Electronic2 {
    default void printDescription() {
        System.out.println("Electronic Description");
    }
}
