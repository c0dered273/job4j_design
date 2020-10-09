package ru.job4j.lsp;

import java.time.LocalDate;
import java.util.List;

public class Warehouse implements Store {
    private final Repository repo;
    private LocalDate now;

    public Warehouse(Repository repo) {
        this.repo = repo;
        this.now = LocalDate.now();
    }

    public Warehouse(Repository repo, LocalDate now) {
        this.repo = repo;
        this.now = now;
    }

    @Override
    public boolean accept(Food f) {
        return f.getExpPercent(now) > 0 && f.getExpPercent(now) <= 25d;
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

    @Override
    public void setNowDate(LocalDate now) {
        this.now = now;
    }

    @Override
    public void emptyStore() {
        repo.clearRepo();
    }
}
