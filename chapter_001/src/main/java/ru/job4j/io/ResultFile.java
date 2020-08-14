package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        System.out.println(builder.toString());
        for (int i = 2; i < 10; i++) {
            for (int j = 2; j < 10; j++) {
                String line = i + " x " + j + " = " + i * j + System.lineSeparator();
                builder.append(line);
            }
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream("table.txt")) {
            fileOutputStream.write(builder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
