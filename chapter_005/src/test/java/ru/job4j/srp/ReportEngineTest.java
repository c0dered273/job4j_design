package ru.job4j.srp;

import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenNewGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker01 = new Employee("Ivan", now, now, 100);
        Employee worker02 = new Employee("Pavel", now, now, 300);
        Employee worker03 = new Employee("Vasili", now, now, 200);
        store.add(worker01);
        store.add(worker02);
        store.add(worker03);
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<h1>")
                .append("Name; Salary;")
                .append("</h1>")
                .append(System.lineSeparator())
                .append(worker02.getName()).append(";")
                .append(NewReport.getTaxSalary(worker02.getSalary())).append(";")
                .append(System.lineSeparator())
                .append(worker03.getName()).append(";")
                .append(NewReport.getTaxSalary(worker03.getSalary())).append(";")
                .append(System.lineSeparator())
                .append(worker01.getName()).append(";")
                .append(NewReport.getTaxSalary(worker01.getSalary())).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generateNew(em -> true), is(expect.toString()));
    }
}