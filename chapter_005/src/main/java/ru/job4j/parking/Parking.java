package ru.job4j.parking;

public interface Parking {
    boolean add(Vehicle vehicle);

    boolean remove(Vehicle vehicle);

    int getFreePlace();
}
