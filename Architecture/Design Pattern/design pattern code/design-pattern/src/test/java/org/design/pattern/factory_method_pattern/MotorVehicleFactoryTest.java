package org.design.pattern.factory_method_pattern;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MotorVehicleFactoryTest {
    @Test
    void carFactoryTest() {
        MotorVehicleFactory carFactory = new CarFactory();
        MotorVehicle motorVehicle = carFactory.create();
        assertThat(motorVehicle).isInstanceOf(Car.class);
    }

    @Test
    void motorCycleTest() {
        MotorVehicleFactory motorcycleFactory = new MotorcycleFactory();
        MotorVehicle motorVehicle = motorcycleFactory.create();
        assertThat(motorVehicle).isInstanceOf(Motorcycle.class);
    }
}