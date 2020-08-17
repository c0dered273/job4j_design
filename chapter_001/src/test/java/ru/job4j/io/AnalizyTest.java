package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

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

    @Test
    public void whenTestWithTempFolder() throws IOException {
        File source = folder.newFile("source.log");
        File target = folder.newFile("target.log");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("    ");
            out.println("200 10:57:01");
            out.println("    ");
            out.println("400 10:58:01");
            out.println("    ");
            out.println("200 10:59:01");
            out.println("    ");
            out.println("500 11:01:02");
            out.println("    ");
            out.println("200 11:02:02");
            out.println("    ");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> expect = new ArrayList<>();
        expect.add("10:58:01;10:59:01");
        expect.add("11:01:02;11:02:02");
        List<String> rsl = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(target.toPath())) {
            reader.lines().forEach(rsl::add);
        }
        assertThat(rsl, is(expect));
    }
}