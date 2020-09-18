package ru.job4j.srp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class HRReport implements ReportGenerator {
    private String name = "HRReport";

    public HRReport() {
    }

    public HRReport(String name) {
        this.name = name;
    }

    @Override
    public Report generate(Store store, Predicate<Employee> filter) {
        List<Employee> result = store.findBy(filter);
        result.sort(Employee.SALARY_REVERSE_ORDER);
        String header = "Name;Salary;";
        List<String> content = new ArrayList<>();
        for (Employee employee : result) {
            String text = employee.getName() + ";" +
                    employee.getSalary() + ";";
            content.add(text);
        }
        return new Report(name, header, content);
    }
}
