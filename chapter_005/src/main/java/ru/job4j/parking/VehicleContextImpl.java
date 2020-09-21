package ru.job4j.parking;

public class VehicleContextImpl implements VehicleContext {
    private final Vehicle vehicle;

    public VehicleContextImpl(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public int park() {
        return 0;
    }
}
