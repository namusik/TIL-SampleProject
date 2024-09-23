package org.design.pattern.facade_pattern;

public class FacadePatternMain {
    public static void main(String[] args) {
        ComputerFacade computerFacade = new ComputerFacade();
        computerFacade.start();
        computerFacade.shutDown();
    }
}
