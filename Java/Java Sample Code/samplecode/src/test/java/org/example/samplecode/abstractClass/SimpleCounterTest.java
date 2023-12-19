package org.example.samplecode.abstractClass;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleCounterTest {
    @Test
    void callSuperClassConstruction() {
        Counter counter = new SimpleCounter();

        Assertions.assertThat(counter.value).isEqualTo(0);
    }

//    @Test
//    void constructor() {
//        Counter counter = new SimpleCounter(3);
//        System.out.println("counter.value = " + counter.value);
//    }


}