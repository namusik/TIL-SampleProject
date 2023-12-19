package org.example.samplecode.abstractClass;

public class FuelCar extends Car{
    String fuel;

    public FuelCar(String fuel) {
        this.fuel = fuel;
    }

    @Override
    String getInformation() {
        return "Fuel Car" + "\nFuel type: " + fuel;
    }
}
