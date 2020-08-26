package ru.job4j.chat;

import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

public class Phrases implements PhraseGenerator {
    private final List<String> lines;

    public Phrases(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public String getString() {
        Random rnd = new Random();
        int lineNum = rnd.nextInt(lines.size() - 1);
        String line = lines.get(lineNum);
        String[] words = line.trim().split("\\s++");
        int numOfWords = rnd.nextInt(words.length - 1) + 1;
        int pos = rnd.nextInt(words.length - numOfWords);
        StringJoiner builder = new StringJoiner(" ");
        for (int i = pos; i < words.length - numOfWords; i++) {
            builder.add(words[i]);
        }
        return builder.toString();
    }
}
