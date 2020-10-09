package ru.job4j.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Food {
    private String name;
    private LocalDate expireDate;
    private LocalDate createDate;
    private int price;
    private double discount;

    public Food(String name, LocalDate expireDate, LocalDate createDate, int price, double discount) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getExpPercent(LocalDate now) {
        long expFull = ChronoUnit.DAYS.between(
                getCreateDate(),
                getExpireDate()
        );
        long expRemaining = ChronoUnit.DAYS.between(
                getCreateDate(),
                now
        );
        return (expRemaining / (double) expFull) * 100;
    }
}
