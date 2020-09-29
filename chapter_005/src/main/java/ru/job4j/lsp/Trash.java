package ru.job4j.lsp;

import java.util.List;

public class Trash implements Store {
    private final Repository repo;

    public Trash(Repository repo) {
        this.repo = repo;
    }

    @Override
    public boolean accept(Food f) {
        return f.getExpPercent() < 0d || f.getExpPercent() > 100d;
    }

    @Override
    public void add(Food f) {
        if (accept(f)) {
            repo.store(f);
        }
    }

    @Override
    public List<Food> getAll() {
        return repo.getAll();
    }

    @Override
    public List<Food> getFoodByName(String name) {
        return repo.getFoodByName(name);
    }
}
