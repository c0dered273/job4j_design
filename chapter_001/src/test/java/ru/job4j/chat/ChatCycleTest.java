package ru.job4j.chat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
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
    public void whenChat() throws NoSuchFieldException, IllegalAccessException {
        UserInput userInput = new StubInput(Arrays.asList("привет", "стоп", "продолжить", "закончить"));
        PhraseGenerator phrases = new StubPhrases(Arrays.asList("phrase1", "phrase2"));
        StubLogger logger = new StubLogger();
        ChatCycle chat = new ChatCycle(userInput, phrases, logger);
        chat.startChat();
        Field uName = chat.getClass().getDeclaredField("USER_NAME");
        uName.setAccessible(true);
        String userName = (String) uName.get(chat);
        Field bName = chat.getClass().getDeclaredField("BOT_NAME");
        bName.setAccessible(true);
        String botName = (String) bName.get(chat);
        List<String> result = logger.getList();
        List<String> expect = Arrays.asList(userName + "привет", botName + "phrase1",
                userName + "стоп", userName + "продолжить",
                botName + "phrase2", userName + "закончить");
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
        List<String> expectWrite = Arrays.asList("Phrase1" + System.lineSeparator(),
                "Phrase2" + System.lineSeparator(),
                "Phrase3" + System.lineSeparator());
        FileWriter fileWriter = new FileWriter(log.toPath());
        fileWriter.writeLines(expectWrite);
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
        public String readLine() {
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
        public void logLine(String line, boolean isConsoleOut) {
            output.add(line);
        }

        @Override
        public void writeLogToFile() {

        }

        public List<String> getList() {
            return output;
        }
    }

}