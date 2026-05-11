package org.design.pattern.abstract_factory_pattern;

import org.assertj.core.api.Assertions;
import org.design.pattern.creational_patterns.abstract_factory.AnimalAbstractFactory;
import org.design.pattern.creational_patterns.abstract_factory.AnimalEra;
import org.design.pattern.creational_patterns.abstract_factory.AnimalType;
import org.design.pattern.creational_patterns.abstract_factory.animal.Animal;
import org.design.pattern.creational_patterns.abstract_factory.animal.SkyAnimal;
import org.junit.jupiter.api.Test;

class AnimalAbstractFactoryTest {
    @Test
    void createAnimal() {
        AnimalAbstractFactory animalAbstractFactory = new AnimalAbstractFactory();
        Animal animal = animalAbstractFactory.createAnimal(AnimalEra.CENOZOIC, AnimalType.SKY);
        animal.create();
        Assertions.assertThat(animal).isInstanceOf(SkyAnimal.class);
    }

}