package ru.job4j.di;

import java.util.Scanner;

public class ConsoleInput {

    public String getLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
