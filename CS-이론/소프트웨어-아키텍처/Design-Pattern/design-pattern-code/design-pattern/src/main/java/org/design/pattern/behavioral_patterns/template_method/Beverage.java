package org.design.pattern.behavioral_patterns.template_method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
abstract class Beverage {
    // Template Method
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    void boilWater() {
      log.info("Boiling water");
    }

    void pourInCup() {
        log.info("Pouring into cup");
    }

    abstract void brew();
    abstract void addCondiments();
}