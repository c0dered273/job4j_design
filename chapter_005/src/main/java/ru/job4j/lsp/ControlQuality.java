package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void handle(Food food) {
        stores.forEach(s -> s.add(food));
    }

    public void resort() {
        List<Food> foodList = getAllFood();
        emptyStores();
        foodList.forEach(this::handle);
    }

    private List<Food> getAllFood() {
        List<Food> rsl = new ArrayList<>();
        stores.forEach(s -> rsl.addAll(s.getAll()));
        return rsl;
    }

    private void emptyStores() {
        stores.forEach(Store::emptyStore);
    }
}
