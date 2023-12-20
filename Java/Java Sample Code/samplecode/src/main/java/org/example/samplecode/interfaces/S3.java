package org.example.samplecode.interfaces;

public class S3 extends Tesla{
    @Override
    public int getElectricityUse() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    void tesla() {
        System.out.println("tesla 구현");
    }
}
