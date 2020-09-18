package ru.job4j.srp;

import java.util.function.Predicate;

public interface ReportGenerator {
    Report generate(Store store, Predicate<Employee> filter);
}
