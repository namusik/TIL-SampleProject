package org.practice.basic.modelmapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ModelMapperBasicTest {
    private ModelMapper modelMapper;

    @BeforeEach
    @DisplayName("modelmapper 인스턴스 생성")
    void genenrateModelMapper() {
        modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Test
    @DisplayName("필드명과 필드 타입 동일한 객체 modelmapper")
    void objectModelMapping() {
        Person person = new Person("John Doe", 30, "ignore");

        PersonDTO personDTO = new PersonDTO();

        modelMapper.map(person, personDTO);

        System.out.println("personDTO = " + personDTO);

        PersonDTO personDTO2 = modelMapper.map(person, PersonDTO.class);

        System.out.println("personDTO2 = " + personDTO2);

        assertThat(personDTO.getName()).isEqualTo(person.getName());
    }

    @Test
    @DisplayName("특정 객체의 리스트를 필드명과 필드타입이 동일한 객체의 리스트로 mapping")
    void listMapping() {

        List<Person> persons = Arrays.asList(
                new Person("John Doe", 30),
                new Person("Jane Doe", 25)
        );

        // TypeToken을 사용하여 제네릭 타입 정보를 유지
        List<PersonDTO> personDTOs = modelMapper.map(persons, new TypeToken<List<PersonDTO>>(){}.getType());

        // 결과 출력
        personDTOs.forEach(dto -> System.out.println("Name: " + dto.getName() + ", Age: " + dto.getAge()));

        assertThat(personDTOs.get(0).getName()).isEqualTo(persons.get(0).getName());
    }

    @Test
    @DisplayName("일치하지 않는 타입의 list 끼리의 mapping 실패 사례")
    void wrongListMapping() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);

        List<Character> characters = new ArrayList<>();
        modelMapper.map(integers, characters);

        // Integer 타입의 요소를 Character 타입으로 자동 변환하는 것은 ModelMapper의 기본 설정에 포함되어 있지 않음.
        System.out.println("characters.size() = " + characters.size());

        assertThat(characters.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("일치하지 않는 타입의 list 끼리의 mapping 성공 사례")
    void correctListMapping() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);

        List<Character> characters2 = modelMapper.map(integers, new TypeToken<List<Character>>() {}.getType() );

        System.out.println("characters2.size() = " + characters2.size());

        assertThat(characters2.size()).isEqualTo(3);
    }

    @AllArgsConstructor
    @Getter
    static class Person {
        private String name;
        private int age;
        private String ignoreField;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    @Getter
    @ToString
    static class PersonDTO {
        private String name;
        private int age;
    }

}