package ru.job4j.lsp;

import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void handle(Food food) {
        stores.forEach(s -> s.add(food));
    }
}
