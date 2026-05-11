package org.design.pattern.behavioral_patterns.state;

import org.junit.jupiter.api.Test;

class StateSingletonTest {
    @Test
    void stateSingletonTest() {
        VideoPlayer player = new VideoPlayer();

        player.play();
        player.play();
        player.stop();
        player.play();
        player.stop();
        player.stop();
        player.stop();
    }
}