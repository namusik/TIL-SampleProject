package org.practice.basic.testdoubles;

import java.util.HashMap;
import java.util.Map;

public class FakeRepository {
    private final Map<String, String> fakeMap = new HashMap<>();

    public void save(String name, String address) {
        fakeMap.put(name, address);
    }

    public String getByName(String name) {
        return fakeMap.get(name);
    }

    public Map<String, String> getFakeMap() {
        return fakeMap;
    }
}
