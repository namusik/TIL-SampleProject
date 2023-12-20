package org.example.samplecode.interfaces;

public interface Electronic2 {
    default void printDescription() {
        System.out.println("Electronic Description");
    }
}
