package org.design.pattern.creational_patterns.abstract_factory.animal;

import org.design.pattern.creational_patterns.abstract_factory.AnimalEra;
import org.design.pattern.creational_patterns.abstract_factory.AnimalType;

public abstract class Animal {
    AnimalType type;
    AnimalEra era;
    String name;

    Animal(AnimalType type, AnimalEra era, String name) {
        this.type = type;
        this.era = era;
        this.name = name;
    }

    public abstract void create();
}