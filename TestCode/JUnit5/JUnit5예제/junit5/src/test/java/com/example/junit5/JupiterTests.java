package com.example.junit5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JupiterTests {

    @Test
    @DisplayName("여기에는 테스트 제목을 넣어요")
    void test() {
        int a = 1;
        int b = 1;
        Assertions.assertThat(a).isEqualTo(b);
    }
}
