package org.practice.basic.abstractClass;

public class SimpleCounter extends Counter{
    public SimpleCounter() {
        super(3);
        System.out.println("dfdf");
    }

    public SimpleCounter(int value) {
        super(value);
    }

    @Override
    int increment() {
        return ++value;
    }
}
