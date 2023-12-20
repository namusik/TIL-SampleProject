package org.example.samplecode.interfaces;

public class Computer implements Electronic, Electronic2{
    @Override
    public int getElectricityUse() {
        return 0;
    }

    @Override
    public void printDescription() {
        Electronic.super.printDescription();
    }

    @Override
    public String getName() {
        return "상속한 인터페이스 메서드";
    }
}
