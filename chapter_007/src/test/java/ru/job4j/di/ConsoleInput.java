package ru.job4j.di;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleInput {

    public String getLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
