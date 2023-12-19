package org.example.samplecode.var;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VarEx {
    @Test
    void varTest() {
        var a = "aa";
        System.out.println("a.getClass().getTypeName() = " + a.getClass().getTypeName());
        assertThat(a).isInstanceOf(String.class);

        a = "bbb";
        System.out.println("a = " + a);
    }

    @Test
    void varAnonymousClass() {
        var obj = new Object(){};
        System.out.println("obj. = " + obj.getClass().getTypeName());
        assertThat(obj.getClass()).isNotEqualTo(Object.class);

//        obj = new Object();
    }
}
