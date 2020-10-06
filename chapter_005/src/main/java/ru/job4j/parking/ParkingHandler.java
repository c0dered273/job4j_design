package ru.job4j.parking;

import java.util.List;

public class ParkingHandler {
    private final Parking parking;

    public ParkingHandler(Parking parking) {
        this.parking = parking;
    }

    public void addToParking(List<Vehicle> vehicles) {
        vehicles.forEach(parking::add);
    }

    public void delFromParking(List<Vehicle> vehicles) {
        vehicles.forEach(parking::remove);
    }
}
