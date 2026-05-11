package org.design.pattern.creational_patterns.abstract_factory;

import org.design.pattern.creational_patterns.abstract_factory.animal.Animal;
import org.design.pattern.creational_patterns.abstract_factory.factory.CenozoicAnimalFactory;
import org.design.pattern.creational_patterns.abstract_factory.factory.MesozoicAnimalFactory;

public class AnimalAbstractFactory {
    private Animal animal;

    public Animal createAnimal(AnimalEra era ,AnimalType type) {
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
