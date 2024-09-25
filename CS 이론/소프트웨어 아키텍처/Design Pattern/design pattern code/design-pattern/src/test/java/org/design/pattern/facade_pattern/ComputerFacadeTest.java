package org.design.pattern.facade_pattern;

import org.design.pattern.structural.facade_pattern.ComputerFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ComputerFacadeTest {
    @Test
    @DisplayName("facade pattern test")
    void testComputerFacade() {
        ComputerFacade computerFacade = new ComputerFacade();
        computerFacade.start();
        computerFacade.shutDown();
    }
}