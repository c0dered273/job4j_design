package ru.job4j.parking;

public interface Parking {
    boolean place(int size);
    boolean remove(int size);
    int getFreePlaces();
}
