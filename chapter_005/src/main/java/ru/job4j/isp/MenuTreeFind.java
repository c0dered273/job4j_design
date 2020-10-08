package ru.job4j.isp;

import java.util.Optional;

public interface MenuTreeFind {

    Optional<MenuItem> findByName(String name);
}
