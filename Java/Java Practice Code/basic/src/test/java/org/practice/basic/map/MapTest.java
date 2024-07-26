package org.practice.basic.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

@Slf4j
public class MapTest {
    @Test
    void emptyMap() {
        Map<String, Long> emptyMap = Collections.emptyMap();

        log.info("emptyMap: {}", emptyMap);
    }
}
