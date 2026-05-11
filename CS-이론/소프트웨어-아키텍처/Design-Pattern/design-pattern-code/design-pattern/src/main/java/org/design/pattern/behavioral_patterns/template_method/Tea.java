package org.design.pattern.behavioral_patterns.template_method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Tea extends Beverage{
    @Override
    void brew() {
      log.info("Tea brew");
    }

    @Override
    void addCondiments() {
        log.info("add lemon");
    }
}
