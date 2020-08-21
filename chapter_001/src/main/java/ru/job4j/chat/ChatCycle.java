package ru.job4j.chat;


import java.io.IOException;

public class ChatCycle {
    private final UserInput userInput;
    private final ChatLogger log;
    private final PhraseGenerator phrases;

    public ChatCycle(UserInput userInput, PhraseGenerator phrases, ChatLogger log) {
        this.userInput = userInput;
        this.log = log;
        this.phrases = phrases;
    }

    public void startChat() {
        boolean isActive = true;
        boolean isAnswer = true;
        String name = "I: ";
        String bot = "Bot: ";
        while (isActive) {
            String input = "";
            try {
                System.out.print(name);
                input = userInput.readLine();
            } catch (IOException exception) {
                isActive = false;
                System.out.println("Console input error");
                exception.printStackTrace();
            }
            switch (input) {
                case ("стоп") -> isAnswer = false;
                case ("продолжить") -> isAnswer = true;
                case ("закончить") -> {
                    isActive = false;
                    isAnswer = false;
                }
            }
            try {
                log.writeLine(name + input, false);
                if (isAnswer) {
                    log.writeLine(bot + phrases.getString());
                }
            } catch (IOException exception) {
                isActive = false;
                System.out.println("File write error");
                exception.printStackTrace();
            }
        }
    }
}
