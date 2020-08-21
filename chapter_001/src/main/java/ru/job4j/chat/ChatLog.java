package ru.job4j.chat;

import java.io.IOException;

public class ChatLog implements ChatLogger {
    private final FileWriter fileWriter;

    public ChatLog(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    @Override
    public void writeLine(String line, boolean isConsole) throws IOException {
        fileWriter.writeLine(line + System.lineSeparator());
        if (isConsole) {
            System.out.println(line);
        }
    }
}
