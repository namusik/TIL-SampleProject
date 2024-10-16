package org.design.pattern.behavioral_patterns.template_method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Coffee extends Beverage{
    @Override
    void brew() {
      log.info("Coffee brew");
    }

    @Override
    void addCondiments() {
        log.info("Add sugar");
    }
}
