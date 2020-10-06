package ru.job4j.parking;

public interface Parking {
    boolean add(Car car);

    boolean remove(Car car);

    int getFreePlace();
}
