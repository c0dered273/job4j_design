package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Async mailing service.
 */
public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    /**
     * Asynchronous sends email to user.
     *
     * @param user User data
     */
    public void emailTo(User user) {
        pool.submit(() -> {
            String subject = String.format("Notification %s to email %s",
                    user.getUsername(), user.getEmail());
            String body = String.format(" Add a new event to %s", user.getUsername());
            send(subject, body, user.getEmail());
        });
    }

    /**
     * Closes thread pool.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {
        // TODO: Implement method.
    }
}
