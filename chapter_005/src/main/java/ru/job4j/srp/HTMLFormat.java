package ru.job4j.srp;

import java.util.StringJoiner;

public class HTMLFormat implements ReportFormat {
    @Override
    public String formatTo(Report report) {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        result.add("<html>");
        result.add("<body>");
        result.add("<h1>");
        result.add(report.getName());
        result.add("</h1>");
        result.add("<p>");
        result.add("<br>");
        result.add(report.getHeader());
        report.getContent().forEach(i -> {
            result.add("<br>");
            result.add(i);
        } );
        result.add("</p>");
        result.add("</body>");
        result.add("</html>");
        return result.toString();
    }
}
