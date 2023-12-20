package org.example.samplecode.interfaces;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ComputerTest {
    @Test
    void interfaceTest() {
        Electronic computer = new Computer();
        int electricityUse = computer.getElectricityUse();
        assertThat(electricityUse).isEqualTo(0);

        computer.printDescription();

        boolean result = Electronic.isEnergyEfficient("LED");

        assertThat(result).isTrue();

        System.out.println("private method 호출 시작");
        computer.bar();

        System.out.println("private static method 호출 시작 ");
        Electronic.buzz();

        computer.getName();
    }

}