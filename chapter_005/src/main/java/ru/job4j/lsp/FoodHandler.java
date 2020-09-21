package ru.job4j.lsp;

public class FoodHandler {
    private final Store store;

    public FoodHandler(Store store) {
        this.store = store;
    }

    public boolean storeFood(Food food) {
        return store.store(food);
    }
}
