package ru.job4j.srp;

import java.util.StringJoiner;

public class PlainTextFormat implements ReportFormat {

    @Override
    public String formatTo(Report report) {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        result.add(report.getName());
        result.add(report.getHeader());
        report.getContent().forEach(result::add);
        return result.toString();
    }
}
