package org.design.pattern.creational_patterns.abstract_factory.animal;

import org.design.pattern.creational_patterns.abstract_factory.AnimalEra;
import org.design.pattern.creational_patterns.abstract_factory.AnimalType;

public class SkyAnimal extends Animal {
    public SkyAnimal(AnimalEra era, String name) {
        super(AnimalType.SKY, era, name);
        create();
    }

    @Override
    public void create() {
        System.out.println("Createing a " + type + " animal" + name);

    }

}
