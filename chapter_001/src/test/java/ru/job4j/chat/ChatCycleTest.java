package ru.job4j.chat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ChatCycleTest {
    File answers;
    File log;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void init() throws IOException {
        answers = folder.newFile("answers.txt");
        log = folder.newFile("chat.log");
    }

    @Test
    public void whenStartChat() {
        UserInput userInput = new StubInput(Arrays.asList("привет", "пока", "закончить"));
        PhraseGenerator phrases = new StubPhrases(Arrays.asList("phrase1", "phrase2"));
        StubLogger logger = new StubLogger();
        ChatCycle chat = new ChatCycle(userInput, phrases, logger);
        chat.startChat();
        List<String> result = logger.getList();
        List<String> expect = Arrays.asList("I: привет", "Bot: phrase1", "I: пока", "Bot: phrase2", "I: закончить");
        assertThat(result, is(expect));
    }

    @Test
    public void whenStopAnswer() {
        UserInput userInput = new StubInput(Arrays.asList("привет", "стоп", "продолжить", "закончить"));
        PhraseGenerator phrases = new StubPhrases(Arrays.asList("phrase1", "phrase2"));
        StubLogger logger = new StubLogger();
        ChatCycle chat = new ChatCycle(userInput, phrases, logger);
        chat.startChat();
        List<String> result = logger.getList();
        List<String> expect = Arrays.asList("I: привет", "Bot: phrase1", "I: стоп", "I: продолжить", "Bot: phrase2", "I: закончить");
        assertThat(result, is(expect));
    }

    @Test
    public void whenReadAnswers() throws IOException {
        try (PrintWriter writer = new PrintWriter(answers)) {
            writer.println("Phrase1");
            writer.println("Phrase2");
            writer.println();
            writer.println("Phrase3");
        }
        FileReader reader = new FileReader(answers.toPath());
        List<String> result = reader.readList();
        List<String> expect = Arrays.asList("Phrase1", "Phrase2", "Phrase3");
        assertThat(result, is(expect));
    }

    @Test
    public void whenWriteLog() throws IOException {
        List<String> expect = Arrays.asList("Phrase1", "Phrase2", "Phrase3");
        FileWriter fileWriter = new FileWriter(log.toPath());
        for (String line : expect) {
            fileWriter.writeLine(line);
            fileWriter.writeLine(System.lineSeparator());
        }
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(log.toPath())) {
            reader.lines().forEach(result::add);
        }
        assertThat(result, is(expect));
    }

    public static class StubInput implements UserInput {
        private final List<String> questions;
        private int cursor;

        public StubInput(List<String> questions) {
            this.questions = questions;
        }

        @Override
        public String readLine() throws IOException {
            if (cursor >= questions.size()) cursor = 0;
            return questions.get(cursor++);
        }
    }

    public static class StubPhrases implements PhraseGenerator {
        private final List<String> phrases;
        private int cursor;

        public StubPhrases(List<String> phrases) {
            this.phrases = phrases;
        }

        @Override
        public String getString() {
            if (cursor >= phrases.size()) cursor = 0;
            return phrases.get(cursor++);
        }
    }

    public static class StubLogger implements ChatLogger {
        private final List<String> output = new ArrayList<>();

        @Override
        public void writeLine(String line, boolean isConsoleOut) throws IOException {
            output.add(line);
        }

        public List<String> getList() {
            return output;
        }
    }

}