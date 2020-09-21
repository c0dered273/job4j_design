package ru.job4j.lsp;

import java.time.LocalDate;

public class Bread extends Food {
    public Bread(String name, LocalDate expireDate, LocalDate createDate, int price, double discount) {
        super(name, expireDate, createDate, price, discount);
    }
}
