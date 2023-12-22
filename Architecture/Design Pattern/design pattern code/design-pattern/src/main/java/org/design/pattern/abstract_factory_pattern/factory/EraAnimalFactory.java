package org.design.pattern.abstract_factory_pattern.factory;

import org.design.pattern.abstract_factory_pattern.animal.LandAnimal;
import org.design.pattern.abstract_factory_pattern.animal.SkyAnimal;

public interface EraAnimalFactory {
    LandAnimal makeLandAnimal();

    SkyAnimal makeSkyAnimal();
}
