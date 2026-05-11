package org.practice.basic.anonymousClass;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Animal {
    private String name;

    public void sound() {
        System.out.println("animal");
    }
}
