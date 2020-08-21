package ru.job4j.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    private final Path file;

    public FileReader(Path file) {
        this.file = file;
    }

    public List<String> readList() throws IOException {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            reader.lines()
                    .filter(s -> !s.isEmpty() || !s.isBlank())
                    .forEach(rsl::add);
        }
        return rsl;
    }
}
