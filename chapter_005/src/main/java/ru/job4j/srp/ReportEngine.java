package ru.job4j.srp;

import java.util.function.Predicate;

public class ReportEngine {
    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    public String generate(ReportGenerator generator, Predicate<Employee> filter, ReportFormat format) {
        Report report = generator.generate(store, filter);
        return format.formatTo(report);
    }
}
