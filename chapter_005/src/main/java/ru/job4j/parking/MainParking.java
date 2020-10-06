package ru.job4j.parking;

public class MainParking implements Parking {
    private final int maxSize;
    private final Storage cars;
    private int size;

    public MainParking(int maxSize, Storage storage) {
        this.maxSize = maxSize;
        this.cars = storage;
    }

    @Override
    public boolean add(Vehicle vehicle) {
        boolean rsl = false;
        int carSize = vehicle.getSize();
        if (carSize <= getFreePlace()) {
            cars.put(vehicle.getNumberPlate(), vehicle);
            size += carSize;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean remove(Vehicle vehicle) {
        boolean rsl = false;
        if (cars.remove(vehicle.getNumberPlate()) != null) {
            size -= vehicle.getSize();
            rsl = true;
        }
        return rsl;
    }

    @Override
    public int getFreePlace() {
        return maxSize - size;
    }
}
