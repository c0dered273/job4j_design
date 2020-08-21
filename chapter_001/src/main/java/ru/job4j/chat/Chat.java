package ru.job4j.chat;

import java.io.IOException;
import java.nio.file.Paths;

public class Chat {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("invalid command line parameters");
            System.exit(64);
        }
        UserInput consoleInput = new ConsoleInput();
        FileReader answers = new FileReader(Paths.get(args[0]));
        PhraseGenerator phrases = null;
        try {
            phrases = new Phrases(answers.readList());
        } catch (IOException e) {
            System.out.println("File read error");
            e.printStackTrace();
        }
        ChatLogger chatLog = new ChatLog(new FileWriter(Paths.get(args[1])));
        ChatCycle chatCycle = new ChatCycle(consoleInput, phrases, chatLog);
        chatCycle.startChat();
    }
}
