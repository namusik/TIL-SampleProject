package org.example.samplecode.abstractClass;

import org.springframework.beans.factory.annotation.Autowired;
public abstract class BallService {
    private LogRepository logRepository;

    public BallService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }
}
