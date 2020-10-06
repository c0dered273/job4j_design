package ru.job4j.parking;

import java.util.Objects;

public abstract class Vehicle {
    private final String numberPlate;
    private final int size;

    public Vehicle(String numberPlate, int size) {
        this.numberPlate = numberPlate;
        this.size = size;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(numberPlate, vehicle.numberPlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberPlate);
    }
}
