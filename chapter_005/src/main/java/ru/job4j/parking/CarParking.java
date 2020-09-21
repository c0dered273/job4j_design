package ru.job4j.parking;

public class CarParking implements Parking {
    private final int maxPlaces;
    private int places;

    public CarParking(int places) {
        this.maxPlaces = places;
        this.places = places;

    }

    @Override
    public boolean place(int size) {
        int newPlaces = places - size;
        if (newPlaces > 0) {
            places = newPlaces;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(int size) {
        int newPlaces = places + size;
        if (newPlaces <= maxPlaces) {
            places = newPlaces;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getFreePlaces() {
        return places;
    }
}
