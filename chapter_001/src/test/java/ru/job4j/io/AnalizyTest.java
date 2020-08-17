package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {
    @Test
    public void whenSelectUnavailable() {
        Analizy analizy = new Analizy();
        List<String> expect = new ArrayList<>();
        expect.add("10:58:01;10:59:01");
        expect.add("11:01:02;11:02:02");
        List<String> result = new ArrayList<>();
        analizy.unavailable("server.log", "unavailable.log");
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("unavailable.log"))) {
            reader.lines().forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(result, is(expect));
    }
}