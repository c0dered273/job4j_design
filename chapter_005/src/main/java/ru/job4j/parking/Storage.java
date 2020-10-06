package ru.job4j.parking;

public interface Storage {
    Car put(String plate, Car car);
    Car remove(String plate);
}
