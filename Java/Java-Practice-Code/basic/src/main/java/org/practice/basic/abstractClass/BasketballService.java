package org.practice.basic.abstractClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasketballService extends BallService{
    @Autowired
    public BasketballService(LogRepository logRepository) {
        super(logRepository);
    }
}
