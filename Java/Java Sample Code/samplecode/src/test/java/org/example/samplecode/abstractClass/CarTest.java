package org.example.samplecode.abstractClass;

import org.junit.jupiter.api.Test;

public class CarTest {
    @Test
    void carTest() {
        Car car = new ElectricCar(30);
        car.display();

        Car fuelCar = new FuelCar("100");
        fuelCar.display();
    }
}
