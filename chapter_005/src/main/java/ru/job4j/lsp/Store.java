package ru.job4j.lsp;

import java.util.List;

public interface Store {
    boolean accept(Food f);

    void add(Food f);

    List<Food> getAll();

    List<Food> getFoodByName(String name);
}
