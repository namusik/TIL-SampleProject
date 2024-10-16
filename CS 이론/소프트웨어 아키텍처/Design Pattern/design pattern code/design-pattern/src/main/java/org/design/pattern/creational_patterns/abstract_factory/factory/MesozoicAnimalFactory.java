package org.design.pattern.creational_patterns.abstract_factory.factory;

import org.design.pattern.creational_patterns.abstract_factory.AnimalEra;
import org.design.pattern.creational_patterns.abstract_factory.AnimalType;
import org.design.pattern.creational_patterns.abstract_factory.animal.Animal;
import org.design.pattern.creational_patterns.abstract_factory.animal.LandAnimal;
import org.design.pattern.creational_patterns.abstract_factory.animal.SkyAnimal;

public class MesozoicAnimalFactory implements EraAnimalFactory{
    @Override
    public LandAnimal makeLandAnimal() {
        return new LandAnimal(AnimalEra.MESOZOIC, "Tyrannosaurus Rex");
    }

    @Override
    public SkyAnimal makeSkyAnimal() {
        return new SkyAnimal(AnimalEra.MESOZOIC, "Pterodactylus");
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
