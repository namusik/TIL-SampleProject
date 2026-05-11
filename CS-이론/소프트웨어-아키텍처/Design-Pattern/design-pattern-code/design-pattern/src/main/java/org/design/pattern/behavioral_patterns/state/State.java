package org.design.pattern.behavioral_patterns.state;

public interface State {
    void play(VideoPlayer player);

    void stop(VideoPlayer player);
}
