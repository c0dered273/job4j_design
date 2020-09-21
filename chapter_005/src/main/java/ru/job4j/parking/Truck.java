package ru.job4j.parking;

public class Truck implements Vehicle {
    private final int size;

    public Truck(int size) {
        this.size = size;
    }

    @Override
    public int park() {
        return 0;
    }
}
