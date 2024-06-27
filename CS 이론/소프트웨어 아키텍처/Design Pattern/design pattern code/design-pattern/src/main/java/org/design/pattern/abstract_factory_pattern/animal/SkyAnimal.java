package org.design.pattern.abstract_factory_pattern.animal;

import org.design.pattern.abstract_factory_pattern.AnimalEra;
import org.design.pattern.abstract_factory_pattern.AnimalType;

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
