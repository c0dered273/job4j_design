package ru.job4j.chat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {
    private final Path file;

    public FileWriter(Path file) {
        this.file = file;
    }

    public void writeLine(String line) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(line);
        }
    }
}
