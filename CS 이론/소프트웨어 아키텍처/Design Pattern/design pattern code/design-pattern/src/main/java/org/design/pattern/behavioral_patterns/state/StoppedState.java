package org.design.pattern.behavioral_patterns.state;

public class StoppedState implements State{
    @Override
    public void play(VideoPlayer player) {
        System.out.println("Starting the video");
        player.setState(new PlayingState());
    }

    @Override
    public void stop(VideoPlayer player) {
        System.out.println("Video is already stopped");
    }
}
