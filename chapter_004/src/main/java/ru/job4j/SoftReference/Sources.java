package ru.job4j.SoftReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Sources {
    private static final Logger logger
            = LoggerFactory.getLogger(Sources.class);

    public static Function<String, List<String>> getFileSource() {
        return n -> {
            List<String> rsl = new ArrayList<>();
            try (BufferedReader reader = Files.newBufferedReader(Path.of(n))) {
                reader.lines().forEach(rsl::add);
            } catch (IOException e) {
                logger.error("File read error", e);
            }
            return rsl;
        };
    }
}
