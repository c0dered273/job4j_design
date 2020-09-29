package ru.job4j.lsp;

import java.util.List;

public class Shop implements Store {
    private final Repository repo;

    public Shop(Repository repo) {
        this.repo = repo;
    }

    @Override
    public boolean accept(Food f) {
        double expPrc = f.getExpPercent();
        boolean rsl = expPrc > 25d && expPrc <= 100d;
        if (rsl && expPrc > 75d) {
            f.setDiscount(0.5d);
        }
        return rsl;
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
