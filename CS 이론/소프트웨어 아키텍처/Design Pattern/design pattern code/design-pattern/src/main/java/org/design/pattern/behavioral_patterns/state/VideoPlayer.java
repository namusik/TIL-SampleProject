package org.design.pattern.behavioral_patterns.state;

import lombok.Setter;

@Setter
public class VideoPlayer {
    private State state;

    public VideoPlayer() {
        this.state = new StoppedState();
    }

    public void play() {
        state.play(this);
    }

    public void stop() {
        state.stop(this);
    }
}
