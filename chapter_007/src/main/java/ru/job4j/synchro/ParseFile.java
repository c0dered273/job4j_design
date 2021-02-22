package ru.job4j.synchro;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent(Predicate<Integer> pred) throws IOException {
        StringBuilder output;
        try (InputStream i = new FileInputStream(file)) {
            output = new StringBuilder();
            int data;
            while ((data = i.read()) > 0) {
                if (pred.test(data)) {
                    output.append((char) data);
                }
            }
        }
        return output.toString();
    }

    public synchronized String getContent() throws IOException {
        return getContent(i -> true);
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        return getContent(i -> i < 0x80);
    }

    public synchronized void saveContent(String content) throws IOException {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        }
    }
}
