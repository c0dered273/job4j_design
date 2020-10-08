package ru.job4j.isp;

import java.util.List;

public interface MenuItem extends Runnable {
    String getName();

    List<MenuItem> getItems();
}
