package ru.job4j.lsp;

import java.util.List;

public interface Repository {
    boolean store(Food food);

    List<Food> getAll();

    List<Food> getFoodByName(String name);

    void clearRepo();
}
