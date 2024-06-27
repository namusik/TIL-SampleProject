package org.design.pattern.abstract_factory_pattern;

import org.design.pattern.abstract_factory_pattern.animal.Animal;
import org.design.pattern.abstract_factory_pattern.factory.CenozoicAnimalFactory;
import org.design.pattern.abstract_factory_pattern.factory.MesozoicAnimalFactory;

public class AnimalAbstractFactory {
    private Animal animal;

    Animal createAnimal(AnimalEra era ,AnimalType type) {
        switch (era) {
            case MESOZOIC:
                animal = new MesozoicAnimalFactory().createAnimal(type);
                break;
            case CENOZOIC:
                animal = new CenozoicAnimalFactory().createAnimal(type);
                break;
        }
        return animal;
    }
}
