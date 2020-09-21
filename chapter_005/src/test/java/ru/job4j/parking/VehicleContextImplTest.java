package ru.job4j.parking;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VehicleContextImplTest {
    private final Parking carParking = new CarParking(8);
    private final Parking truckParking = new TruckParking(2);

    @Test
    public void whenAddAndRemoveCar() {
    ParkingCoordinator coordinator = new ParkingCoordinatorImpl(carParking, truckParking);
    VehicleContext car = new VehicleContextImpl(new Car());
    coordinator.place(car);
    coordinator.place(car);
    coordinator.remove(car);
    assertThat(carParking.getFreePlaces(), is(7));
    }

    @Test
    public void whenAddAndRemoveTruck() {
        ParkingCoordinator coordinator = new ParkingCoordinatorImpl(carParking, truckParking);
        VehicleContext truck = new VehicleContextImpl(new Truck(4));
        coordinator.place(truck);
        coordinator.place(truck);
        coordinator.remove(truck);
        assertThat(truckParking.getFreePlaces(), is(1));
    }

    @Test
    public void whenCarParkingOverload() {
        ParkingCoordinator coordinator = new ParkingCoordinatorImpl(carParking, truckParking);
        VehicleContext car = new VehicleContextImpl(new Car());
        VehicleContext truck = new VehicleContextImpl(new Truck(4));
        coordinator.place(truck);
        coordinator.place(truck);
        coordinator.place(truck);
        coordinator.place(car);
        assertThat(carParking.getFreePlaces(), is(3));
    }

}