package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream inputStream = new FileInputStream("even.txt")) {
            int read;
            while ((read = inputStream.read()) != -1) {
                stringBuilder.append((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] lines = stringBuilder.toString().split(System.lineSeparator());
        try {
            for (String line : lines) {
                boolean rst = Integer.parseInt(line) % 2 == 0;
                System.out.println(rst);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
