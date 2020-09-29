package ru.job4j.lsp;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ControlQualityTest {
    private final Store wh = new Warehouse(new MemRepository());
    private final Store sh = new Shop(new MemRepository());
    private final Store tr = new Trash(new MemRepository());
    private final List<Store> stores = List.of(wh, sh, tr);

    @Test
    public void whenFoodToWarehouse() {
        LocalDate prod = LocalDate.of(2020, 9, 1);
        LocalDate exp = LocalDate.of(2021, 12, 1);
        Food bread = new Bread("bread", exp, prod, 120, 0);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.handle(bread);
        List<Food> expect = Collections.singletonList(bread);
        assertThat(wh.getFoodByName("bread"), is(expect));
    }

    @Test
    public void whenFoodToShop() {
        LocalDate prod = LocalDate.of(2020, 7, 1);
        LocalDate exp = LocalDate.of(2020, 12, 1);
        Food bread = new Bread("bread", exp, prod, 120, 0);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.handle(bread);
        List<Food> expect = Collections.singletonList(bread);
        assertThat(sh.getFoodByName("bread"), is(expect));
    }

    @Test
    public void whenFoodToShopWithDiscount() {
        LocalDate prod = LocalDate.of(2020, 2, 1);
        LocalDate exp = LocalDate.of(2020, 12, 1);
        Food bread = new Bread("bread", exp, prod, 120, 0);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.handle(bread);
        assertThat(sh.getFoodByName("bread").get(0).getDiscount(), is(0.5d));
    }

    @Test
    public void whenFoodToTrash() {
        LocalDate prod = LocalDate.of(2020, 1, 1);
        LocalDate exp = LocalDate.of(2020, 8, 1);
        Food bread = new Bread("bread", exp, prod, 120, 0);
        ControlQuality controlQuality = new ControlQuality(stores);
        controlQuality.handle(bread);
        List<Food> expect = Collections.singletonList(bread);
        assertThat(tr.getFoodByName("bread"), is(expect));
    }
}