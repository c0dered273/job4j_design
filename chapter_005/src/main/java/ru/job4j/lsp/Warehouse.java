package ru.job4j.lsp;

import java.util.List;

public class Warehouse implements Store {
    private final Repository repo;

    public Warehouse(Repository repo) {
        this.repo = repo;
    }

    @Override
    public boolean store(Food food) {
        repo.store(food);
        return true;
    }

    @Override
    public List<Food> getFoodByName(String name) {
        return repo.getFoodByName(name);
    }
}
