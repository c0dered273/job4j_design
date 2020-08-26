package ru.job4j.chat;


import java.io.IOException;

public class ChatCycle {
    private static final String STOP_ANSWER = "стоп";
    private static final String RESUME_ANSWER = "продолжить";
    private static final String EXIT_PROGRAM = "закончить";
    private static final String USER_NAME = "Я: ";
    private static final String BOT_NAME = "Бот: ";
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
        while (isActive) {
            String input = "";
            try {
                System.out.print(USER_NAME);
                input = userInput.readLine();
            } catch (IOException exception) {
                isActive = false;
                System.out.println("Console input error");
                exception.printStackTrace();
            }
            switch (input) {
                case (STOP_ANSWER) -> isAnswer = false;
                case (RESUME_ANSWER) -> isAnswer = true;
                case (EXIT_PROGRAM) -> {
                    isActive = false;
                    isAnswer = false;
                }
            }
            log.logLine(USER_NAME + input, false);
            if (isAnswer) {
                log.logLine(BOT_NAME + phrases.getString());
            }
        }
        try {
            log.writeLogToFile();
        } catch (IOException exception) {
            System.out.println("File write error");
            exception.printStackTrace();
        }
    }
}
