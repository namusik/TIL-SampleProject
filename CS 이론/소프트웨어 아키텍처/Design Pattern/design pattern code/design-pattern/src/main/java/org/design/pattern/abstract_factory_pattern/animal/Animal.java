package org.design.pattern.abstract_factory_pattern.animal;

import org.design.pattern.abstract_factory_pattern.AnimalEra;
import org.design.pattern.abstract_factory_pattern.AnimalType;

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