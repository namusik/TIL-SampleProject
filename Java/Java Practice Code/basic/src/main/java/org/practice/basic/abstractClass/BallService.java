package org.practice.basic.abstractClass;

public abstract class BallService {
    private LogRepository logRepository;

    public BallService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }
}
