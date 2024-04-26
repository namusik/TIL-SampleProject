package org.example.samplecode.modelmapper;

import lombok.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelMapperEx1 {
    public static void main(String[] args) {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        // 1.modelmapper 객체 map
        Person person = new Person("John Doe", 30, "ignore");

        PersonDTO personDTO = new PersonDTO();

        modelMapper.map(person, personDTO);

        System.out.println("personDTO = " + personDTO);

        PersonDTO personDTO2 = modelMapper.map(person, PersonDTO.class);

        System.out.println("personDTO2 = " + personDTO2);


        
        // 2. modelmapper list mapping
        List<Person> persons = Arrays.asList(
                new Person("John Doe", 30),
                new Person("Jane Doe", 25)
        );

        // TypeToken을 사용하여 제네릭 타입 정보를 유지
        List<PersonDTO> personDTOs = modelMapper.map(persons, new TypeToken<List<PersonDTO>>(){}.getType());

        // 결과 출력
        personDTOs.forEach(dto -> System.out.println("Name: " + dto.getName() + ", Age: " + dto.getAge()));

        // 2.

        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);

        ArrayList<Character> characters = new ArrayList<>();
        modelMapper.map(integers, characters);

        // Integer 타입의 요소를 Character 타입으로 자동 변환하는 것은 ModelMapper의 기본 설정에 포함되어 있지 않음.
        System.out.println("characters.size() = " + characters.size());

        List<Character> characters2 = modelMapper.map(integers, new TypeToken<List<Character>>() {}.getType() );

        System.out.println("characters2.size() = " + characters2.size());

        for (Character c : characters2) {
            System.out.println("c = " + c);
        }
    }

    @AllArgsConstructor
    public static class Person {
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
    public static class PersonDTO {
        private String name;
        private int age;
    }
}
