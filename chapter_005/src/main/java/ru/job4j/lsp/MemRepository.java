package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class MemRepository implements Repository {
    private final List<Food> repo = new ArrayList<>();

    @Override
    public boolean store(Food food) {
        repo.add(food);
        return true;
    }

    @Override
    public List<Food> getAll() {
        return repo;
    }

    @Override
    public List<Food> getFoodByName(String name) {
        List<Food> rsl = new ArrayList<>();
        repo.stream()
                .filter(i -> name.equals(i.getName()))
                .forEach(rsl::add);
        return rsl;
    }
}
