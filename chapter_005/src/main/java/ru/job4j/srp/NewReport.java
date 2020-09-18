package ru.job4j.srp;

import java.util.List;
import java.util.function.Predicate;

public class NewReport implements Report {
    public static double getTaxSalary(double salary) {
        return salary * 1.13D;
    }

    @Override
    public String generate(Store store, Predicate<Employee> filter) {
        List<Employee> result = store.findBy(filter);
        result.sort(Employee.SALARY_REVERSE_ORDER);
        StringBuilder text = new StringBuilder();
        text.append("<h1>");
        text.append("Name; Salary;");
        text.append("</h1>");
        text.append(System.lineSeparator());
        for (Employee employee : result) {
            text.append(employee.getName()).append(";")
                    .append(getTaxSalary(employee.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
