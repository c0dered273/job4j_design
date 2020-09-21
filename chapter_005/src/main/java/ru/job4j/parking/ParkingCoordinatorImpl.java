package ru.job4j.parking;

public class ParkingCoordinatorImpl implements ParkingCoordinator {
    private final Parking carParking;
    private final Parking truckParking;

    public ParkingCoordinatorImpl(Parking carParking, Parking truckParking) {
        this.carParking = carParking;
        this.truckParking = truckParking;
    }

    @Override
    public boolean place(VehicleContext vehicleContext) {
        return false;
    }

    @Override
    public boolean remove(VehicleContext vehicleContext) {
        return false;
    }
}
