package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportEngine {
    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        return new OldReport().generate(store, filter);
    }

    public String generateNew(Predicate<Employee> filter) {
        return new NewReport().generate(store, filter);
    }
}
