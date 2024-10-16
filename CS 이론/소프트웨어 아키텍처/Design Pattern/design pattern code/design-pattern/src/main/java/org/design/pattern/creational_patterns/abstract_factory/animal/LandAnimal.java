package org.design.pattern.creational_patterns.abstract_factory.animal;

import org.design.pattern.creational_patterns.abstract_factory.AnimalEra;
import org.design.pattern.creational_patterns.abstract_factory.AnimalType;

public class LandAnimal extends Animal {
    public LandAnimal(AnimalEra era, String name) {
        super(AnimalType.LAND, era, name);
        create();
    }

    @Override
    public void create() {
        System.out.println("Creating a " + type + " animal: " + name);
    }
}
