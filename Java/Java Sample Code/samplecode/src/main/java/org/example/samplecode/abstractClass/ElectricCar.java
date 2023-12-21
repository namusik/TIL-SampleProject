package org.example.samplecode.abstractClass;

public class ElectricCar extends Car{

    int chargingTime;

    public ElectricCar(int chargingTime) {
        this.chargingTime = chargingTime;
    }

    @Override
    String getInformation() {
        return "Electric Car" + "\nCharging Time: " + chargingTime;
    }

}
