package ru.job4j.chat;

import java.io.IOException;

public interface ChatLogger {
    default void writeLine(String line) throws IOException {
        writeLine(line, true);
    }
    void writeLine(String line, boolean isConsole) throws IOException;
}
