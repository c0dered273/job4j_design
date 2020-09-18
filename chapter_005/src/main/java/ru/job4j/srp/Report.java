package ru.job4j.srp;

import java.util.List;

public class Report {
    private String name;
    private String header;
    private List<String> content;

    public Report(String name, String header, List<String> content) {
        this.name = name;
        this.header = header;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
