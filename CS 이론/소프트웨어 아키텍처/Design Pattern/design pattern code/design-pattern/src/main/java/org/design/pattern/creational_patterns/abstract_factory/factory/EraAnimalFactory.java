package org.design.pattern.creational_patterns.abstract_factory.factory;

import org.design.pattern.creational_patterns.abstract_factory.animal.LandAnimal;
import org.design.pattern.creational_patterns.abstract_factory.animal.SkyAnimal;

public interface EraAnimalFactory {
    LandAnimal makeLandAnimal();

    SkyAnimal makeSkyAnimal();
}
