package org.design.pattern.factory_method_pattern;

public class Motorcycle implements MotorVehicle{
    @Override
    public void build() {
        System.out.println("Build Motorcycle");
    }
}
