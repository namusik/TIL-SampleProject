package org.design.pattern.abstract_factory_pattern;

import org.assertj.core.api.Assertions;
import org.design.pattern.abstract_factory_pattern.animal.Animal;
import org.design.pattern.abstract_factory_pattern.animal.SkyAnimal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalAbstractFactoryTest {
    @Test
    void createAnimal() {
        AnimalAbstractFactory animalAbstractFactory = new AnimalAbstractFactory();
        Animal animal = animalAbstractFactory.createAnimal(AnimalEra.CENOZOIC, AnimalType.SKY);
        animal.create();
        Assertions.assertThat(animal).isInstanceOf(SkyAnimal.class);
    }

}