package ru.job4j.lsp;

import java.time.LocalDate;
import java.util.List;

public interface Store {
    boolean accept(Food f);

    void add(Food f);

    List<Food> getAll();

    List<Food> getFoodByName(String name);

    void setNowDate(LocalDate now);

    void emptyStore();
}
