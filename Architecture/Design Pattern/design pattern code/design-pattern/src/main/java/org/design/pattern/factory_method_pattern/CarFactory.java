package org.design.pattern.factory_method_pattern;

public class CarFactory extends MotorVehicleFactory{
    @Override
    protected MotorVehicle createMotorVehicle() {
        return new Car();
    }
}
