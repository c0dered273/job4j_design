package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(Path source, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.toFile())))) {
            zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Path> listFiles(String dirName, String exclude) throws IOException {
        Predicate<Path> predicate;
        if (exclude.isEmpty()) {
            predicate = p -> true;
        } else {
            predicate = p -> p.toFile().getName().endsWith(exclude);
        }
        SearchFiles searchFiles = new SearchFiles(predicate);
        Files.walkFileTree(Path.of(dirName), searchFiles);
        return searchFiles.getPaths();
    }

    public static void main(String[] args) throws IOException {
//        new Zip().packSingleFile(
//                Paths.get("./chapter_001/pom.xml"),
//                Paths.get("pom.zip")
//        );
        ArgZip argZip = new ArgZip(args);
        if (!argZip.valid()) {
            throw new IllegalArgumentException("Not enough arguments");
        }
        Zip zip = new Zip();
        List<Path> source = zip.listFiles(argZip.directory(), argZip.exclude());
        zip.packFiles(source, Path.of(argZip.output()));
    }
}
