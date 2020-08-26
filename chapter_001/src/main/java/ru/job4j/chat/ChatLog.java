package ru.job4j.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatLog implements ChatLogger {
    private final FileWriter fileWriter;
    private final List<String> logBuffer = new ArrayList<>();

    public ChatLog(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    @Override
    public void logLine(String line, boolean isConsole) {
        logBuffer.add(line + System.lineSeparator());
        if (isConsole) {
            System.out.println(line);
        }
    }

    @Override
    public void writeLogToFile() throws IOException {
        fileWriter.writeLines(logBuffer);
    }
}
