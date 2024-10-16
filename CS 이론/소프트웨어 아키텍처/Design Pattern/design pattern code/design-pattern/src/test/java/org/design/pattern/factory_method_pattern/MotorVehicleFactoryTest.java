package org.design.pattern.factory_method_pattern;

import org.design.pattern.creational_patterns.factory_method.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

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