package ru.job4j.srp;

import org.junit.Test;

import java.util.Calendar;
import java.util.StringJoiner;

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
                .append("OldReport")
                .append(System.lineSeparator())
                .append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";");
        String result = engine.generate(new OldReport(), em -> true, new PlainTextFormat());
        assertThat(result, is(expect.toString()));
    }

    @Test
    public void whenDevGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker01 = new Employee("Ivan", now, now, 100);
        store.add(worker01);
        ReportEngine engine = new ReportEngine(store);
        StringJoiner expect = new StringJoiner(System.lineSeparator());
        expect.add("<html>");
        expect.add("<body>");
        expect.add("<h1>");
        expect.add("DevReport");
        expect.add("</h1>");
        expect.add("<p>");
        expect.add("<br>");
        expect.add("Name;Hired;Fired;Salary;");
        expect.add("<br>");
        expect.add(worker01.getName() + ";"
                + worker01.getHired() + ";"
                + worker01.getFired() + ";"
                + worker01.getSalary() + ";");
        expect.add("</p>");
        expect.add("</body>");
        expect.add("</html>");
        String result = engine.generate(new OldReport("DevReport"), em -> true, new HTMLFormat());
        assertThat(result, is(expect.toString()));
    }

    @Test
    public void whenHRGenerated() {
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
                .append("HRReport")
                .append(System.lineSeparator())
                .append("Name;Salary;")
                .append(System.lineSeparator())
                .append(worker02.getName()).append(";")
                .append(worker02.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker03.getName()).append(";")
                .append(worker03.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker01.getName()).append(";")
                .append(worker01.getSalary()).append(";");
        String result = engine.generate(new HRReport(), em -> true, new PlainTextFormat());
        assertThat(result, is(expect.toString()));
    }

    @Test
    public void whenAccountingGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("AccountingReport")
                .append(System.lineSeparator())
                .append("Name;Hired;Fired;Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(AccountingReport.getTaxSalary(worker.getSalary())).append(";");
        String result = engine.generate(new AccountingReport(), em -> true, new PlainTextFormat());
        assertThat(result, is(expect.toString()));
    }
}