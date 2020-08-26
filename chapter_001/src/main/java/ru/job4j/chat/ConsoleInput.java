package ru.job4j.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInput implements UserInput {
    private final BufferedReader reader;

    public ConsoleInput() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String readLine() throws IOException {
        return reader.readLine();
    }
}
