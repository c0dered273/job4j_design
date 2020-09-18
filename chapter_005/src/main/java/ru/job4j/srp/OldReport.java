package ru.job4j.srp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class OldReport implements ReportGenerator {
    @Override
    public Report generate(Store store, Predicate<Employee> filter) {
        String name = "OldReport";
        String header = "Name; Hired; Fired; Salary;";
        List<String> content = new ArrayList<>();
        StringBuilder text = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary()).append(";");
            content.add(text.toString());
        }
        return new Report(name, header, content);
    }
}
