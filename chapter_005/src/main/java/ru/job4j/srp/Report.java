package ru.job4j.srp;

import java.util.function.Predicate;

public interface Report {
    String generate(Store store, Predicate<Employee> filter);
}
