package ru.job4j.parking;

import java.util.HashMap;
import java.util.Map;

public class MemStorage implements Storage {
    private final Map<String, Vehicle> storage = new HashMap<>();

    @Override
    public Vehicle put(String plate, Vehicle vehicle) {
        return storage.put(plate, vehicle);
    }

    @Override
    public Vehicle remove(String plate) {
        return storage.remove(plate);
    }
}
