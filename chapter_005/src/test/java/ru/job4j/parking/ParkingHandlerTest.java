package ru.job4j.parking;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ParkingHandlerTest {
    Vehicle car1 = new Car("E111YT");
    Vehicle car2 = new Car("E222YT");
    Vehicle car3 = new Car("E333YT");
    Vehicle truck1 = new Truck("T111PT");
    Vehicle truck2 = new Truck("T222PT");
    List<Vehicle> carList = List.of(car1, car2, car3);
    List<Vehicle> truckList = List.of(truck1, truck2);
    Storage memStore = new MemStorage();

    @Test
    public void whenAddCar() {
        int parkingSize = 20;
        Parking parking = new MainParking(parkingSize, memStore);
        ParkingHandler handler = new ParkingHandler(parking);
        handler.addToParking(carList);
        assertThat(parkingSize - 3, is(parking.getFreePlace()));
    }

    @Test
    public void whenAddOverLimit() {
        int parkingSize = 2;
        Parking parking = new MainParking(parkingSize, memStore);
        ParkingHandler handler = new ParkingHandler(parking);
        handler.addToParking(carList);
        assertThat(parking.getFreePlace(), is(0));
    }

    @Test
    public void whenRemoveCar() {
        int parkingSize = 20;
        Parking parking = new MainParking(parkingSize, memStore);
        ParkingHandler handler = new ParkingHandler(parking);
        handler.addToParking(carList);
        handler.delFromParking(List.of(car2));
        assertThat(parking.getFreePlace(), is(parkingSize - 2));
    }

    @Test
    public void whenAddCarsAndTrucks() {
        int parkingSize = 20;
        Parking parking = new MainParking(parkingSize, memStore);
        ParkingHandler handler = new ParkingHandler(parking);
        handler.addToParking(truckList);
        handler.addToParking(carList);
        assertThat(parking.getFreePlace(), is(9));
    }

}