package ru.job4j.chat;

import java.io.IOException;

public interface ChatLogger {
    default void logLine(String line) {
        logLine(line, true);
    }
    void logLine(String line, boolean isConsole);
    void writeLogToFile() throws IOException;
}
