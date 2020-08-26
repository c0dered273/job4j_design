package ru.job4j.chat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileWriter {
    private final Path file;

    public FileWriter(Path file) {
        this.file = file;
    }

    public void writeLines(List<String> lines) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            for (String line : lines) {
                writer.write(line);
            }
        }
    }
}
