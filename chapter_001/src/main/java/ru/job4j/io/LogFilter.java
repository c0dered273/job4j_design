package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    private static final String errorCode = "404";

    public static List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            rsl = in.lines().filter(LogFilter::checkCode)
                    .map(l -> l + System.lineSeparator())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            log.forEach(out::write);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkCode(String line) {
        int endInd = line.lastIndexOf(" ");
        int startInd = endInd - 3;
        return line.substring(startInd, endInd).equals(errorCode);
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}
