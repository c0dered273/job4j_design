package ru.job4j.parking;

public interface ParkingCoordinator {
    boolean place(VehicleContext vehicleContext);
    boolean remove(VehicleContext vehicleContext);
}
