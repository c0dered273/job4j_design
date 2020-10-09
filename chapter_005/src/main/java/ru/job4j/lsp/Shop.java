package ru.job4j.lsp;

import java.time.LocalDate;
import java.util.List;

public class Shop implements Store {
    private final Repository repo;
    private LocalDate now;

    public Shop(Repository repo) {
        this.repo = repo;
        this.now = LocalDate.now();
    }

    public Shop(Repository repo, LocalDate now) {
        this.repo = repo;
        this.now = now;
    }

    @Override
    public boolean accept(Food f) {
        double expPrc = f.getExpPercent(now);
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

    @Override
    public void setNowDate(LocalDate now) {
        this.now = now;
    }

    @Override
    public void emptyStore() {
        repo.clearRepo();
    }
}
