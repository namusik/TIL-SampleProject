package org.example.testdoubles;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class OrderTest {
    private FakeRepository fakeRepository;

    @BeforeEach
    void init() {
        fakeRepository = new FakeRepository();
    }

    @Test
    void order() {
        fakeRepository.save("aaa", "bbb");

        String address = fakeRepository.getByName("aaa");

        Map<String, String> fakeMap = fakeRepository.getFakeMap();

        fakeMap.forEach((key, value) ->
                System.out.println( key + " : " + value));

        assertThat(address).isEqualTo("bbb");
    }

    @Test
    void check() {

        Map<String, String> fakeMap = fakeRepository.getFakeMap();

        fakeMap.forEach((key, value) ->
                System.out.println( key + " : " + value));

    }

}
