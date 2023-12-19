package org.example.samplecode.abstractClass;

public abstract class Counter {
    int value;

    public Counter() {
        this.value = 0;
        System.out.println("Counter No-Argumetns constructor");
    }
    public Counter(int value) {
        this.value = value;
        System.out.println("Parametrized Counter constructor");
    }

    abstract int increment();
}
