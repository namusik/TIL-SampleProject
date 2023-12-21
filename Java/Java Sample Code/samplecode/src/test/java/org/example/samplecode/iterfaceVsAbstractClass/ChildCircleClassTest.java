package org.example.samplecode.iterfaceVsAbstractClass;

import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ChildCircleClassTest {
    @Test
    @DisplayName("subClass에서 부모클래스의 field에 접근할 수 있는지 테스트.")
    @Description("subClass에서 부모클래스의 field에 setter로 접근해서 값을 설정할 수 있고 실제로 적용된다.")
    void abstractClassStateTest() {
        CircleClass redCircle = new ChildCircleClass();
        redCircle.setColor("RED");
        System.out.println(redCircle.getColor());
        assertThat(redCircle.isValid()).isTrue();

        redCircle.changeColor();
        System.out.println(redCircle.getColor());
    }

    @Test
    @DisplayName("구현체")
    void interfaceStateTest() {
        ChildCircleInterface childCircleInterface = new ChildCircleInterface();
        childCircleInterface.setColor("RED");

        assertThat(childCircleInterface.isValid()).isTrue();
    }
}