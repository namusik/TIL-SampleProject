package com.example.tddbook.money;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class MoneyTest {
    @Test
    void multiple() {
        Dollar dollar = new Dollar(5);
        int result = dollar.time(2);
        log.info("result = {}", result);
        assertThat(result).isEqualTo(10);
    }
}
