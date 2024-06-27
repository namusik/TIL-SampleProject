package org.design.pattern.abstract_factory_pattern.factory;

import org.design.pattern.abstract_factory_pattern.AnimalEra;
import org.design.pattern.abstract_factory_pattern.AnimalType;
import org.design.pattern.abstract_factory_pattern.animal.Animal;
import org.design.pattern.abstract_factory_pattern.animal.LandAnimal;
import org.design.pattern.abstract_factory_pattern.animal.SkyAnimal;

public class CenozoicAnimalFactory implements EraAnimalFactory{
    @Override
    public LandAnimal makeLandAnimal() {
        return new LandAnimal(AnimalEra.CENOZOIC, "Mammoth");
    }

    @Override
    public SkyAnimal makeSkyAnimal() {
        return new SkyAnimal(AnimalEra.CENOZOIC, "Terror bird");
    }

    public Animal createAnimal(AnimalType type) {
        switch (type) {
            case LAND:
                return makeLandAnimal();
            case SKY:
                return makeSkyAnimal();
        }
        return null;
    }
}
