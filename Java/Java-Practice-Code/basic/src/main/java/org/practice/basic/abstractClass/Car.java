package org.practice.basic.abstractClass;

public abstract class Car {

    private int distance;

    private Car(int distance) {
        this.distance = distance;
    }

    public Car() {
        this(0);
        System.out.println("Car default constructor");
    }

    abstract String getInformation();

    protected void display() {
        String info = getInformation() + "\nDistance: " + getDistance();
        System.out.println(info);
    }

    // getters

    public int getDistance() {
        return distance;
    }
}
