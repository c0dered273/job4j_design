package ru.job4j.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ControlQuality {
    private final Store wh;
    private final Store sh;
    private final Store tr;

    public ControlQuality(Store warehouse, Store shop, Store trash) {
        this.wh = warehouse;
        this.sh = shop;
        this.tr = trash;
    }

    public void handle(Food food) {
        FoodHandler warehouse = new FoodHandler(wh);
        FoodHandler shop = new FoodHandler(sh);
        FoodHandler trash = new FoodHandler(tr);
        FoodGrade fg = getGrade(food);
        switch (fg) {
            case TO_WAREHOUSE -> warehouse.storeFood(food);
            case TO_SHOP -> shop.storeFood(food);
            case TO_SHOP_DSC -> {
                food.setDiscount(0.5d);
                shop.storeFood(food);
            }
            case TO_TRASH -> trash.storeFood(food);
        }
    }

    private FoodGrade getGrade(Food food) {
        FoodGrade rsl = FoodGrade.TO_TRASH;
        double expPercent = getExpPercent(food);
        if (expPercent < 25d ) {
            rsl = FoodGrade.TO_WAREHOUSE;
        } else if (expPercent < 75d) {
            rsl = FoodGrade.TO_SHOP;
        } else if (expPercent < 100d) {
            rsl = FoodGrade.TO_SHOP_DSC;
        }
        return rsl;
    }

    private double getExpPercent(Food food) {
        long expFull = ChronoUnit.DAYS.between(
                food.getCreateDate(),
                food.getExpireDate()
        );
        long expRemaining = ChronoUnit.DAYS.between(
                food.getCreateDate(),
                LocalDate.now()
        );
        return (expRemaining / (double) expFull) * 100;
    }

    private enum FoodGrade {
        TO_WAREHOUSE,
        TO_SHOP,
        TO_SHOP_DSC,
        TO_TRASH
    }
}
