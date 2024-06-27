package org.design.pattern.factory_method_pattern;

public abstract class MotorVehicleFactory {
    MotorVehicle create() {
        MotorVehicle vehicle = createMotorVehicle();
        vehicle.build();
        return vehicle;
    }

    protected abstract MotorVehicle createMotorVehicle();
}
