package org.design.pattern.creational_patterns.factory_method;

public class Car implements MotorVehicle{
    @Override
    public void build() {
        System.out.println("Build Car");
    }
}
