package ru.job4j.srp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class NewReport implements ReportGenerator {
    public static double getTaxSalary(double salary) {
        return salary * 1.13D;
    }

    public Report generate(Store store, Predicate<Employee> filter) {
        List<Employee> result = store.findBy(filter);
        result.sort(Employee.SALARY_REVERSE_ORDER);
        String name = "NewReport";
        String header = "Name; Salary;";
        List<String> content = new ArrayList<>();
        for (Employee employee : result) {
            String text = employee.getName() + "; " +
                    getTaxSalary(employee.getSalary()) + ";";
            content.add(text);
        }
        return new Report(name, header, content);
    }
}
