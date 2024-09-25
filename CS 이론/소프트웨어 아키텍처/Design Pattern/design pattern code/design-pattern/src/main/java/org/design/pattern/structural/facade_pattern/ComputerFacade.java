package org.design.pattern.structural.facade_pattern;

import org.design.pattern.structural.facade_pattern.subclasses.CPU;
import org.design.pattern.structural.facade_pattern.subclasses.HardDrive;
import org.design.pattern.structural.facade_pattern.subclasses.Memory;

public class ComputerFacade {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void start() {
        cpu.start();
        memory.load();
        hardDrive.read();
        System.out.println("Computer started successfully!");
    }

    public void shutDown() {
        hardDrive.write();
        memory.release();
        cpu.shutDown();
        System.out.println("Computer shut down successfully!");
    }
}
