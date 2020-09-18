package ru.job4j.srp;

import java.util.List;
import java.util.function.Predicate;

public class ReportEngine {
    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    public String generate(Predicate<Employee> filter) {
        OldReport oldReport = new OldReport();
        return oldReport.generate(store, filter);
    }

    public String generateNew(Predicate<Employee> filter) {
        NewReport newReport = new NewReport();
        return newReport.generate(store, filter);
    }
}
