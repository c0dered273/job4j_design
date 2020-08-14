package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> lines = new ArrayList<>();
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().forEach(lines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : lines) {
            int endInd = line.lastIndexOf(" ");
            int startInd = endInd - 3;
            if (line.substring(startInd, endInd).equals("404")) {
                rsl.add(line);
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
