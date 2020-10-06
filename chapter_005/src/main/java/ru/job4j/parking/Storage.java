package ru.job4j.parking;

public interface Storage {
    Vehicle put(String plate, Vehicle vehicle);
    Vehicle remove(String plate);
}
