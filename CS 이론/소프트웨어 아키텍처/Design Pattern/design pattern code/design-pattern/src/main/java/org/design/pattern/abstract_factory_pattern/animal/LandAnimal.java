package org.design.pattern.abstract_factory_pattern.animal;

import org.design.pattern.abstract_factory_pattern.AnimalEra;
import org.design.pattern.abstract_factory_pattern.AnimalType;

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
