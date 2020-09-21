package ru.job4j.parking;

public class TruckParking implements Parking {
    private final int places;

    public TruckParking(int places) {
        this.places = places;
    }

    @Override
    public boolean place(int size) {
        return false;
    }

    @Override
    public boolean remove(int size) {
        return false;
    }

    @Override
    public int getFreePlaces() {
        return 0;
    }
}
