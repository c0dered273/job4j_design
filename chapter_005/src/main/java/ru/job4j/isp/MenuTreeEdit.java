package ru.job4j.isp;

public interface MenuTreeEdit {

    boolean add(MenuItem parent, MenuItem child);

    boolean delete(MenuItem parent, MenuItem child);
}
