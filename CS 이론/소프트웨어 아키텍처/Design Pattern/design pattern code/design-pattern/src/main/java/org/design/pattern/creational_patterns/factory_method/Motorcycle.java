package org.design.pattern.creational_patterns.factory_method;

public class Motorcycle implements MotorVehicle{
    @Override
    public void build() {
        System.out.println("Build Motorcycle");
    }
}
