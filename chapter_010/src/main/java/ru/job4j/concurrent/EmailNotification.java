package ru.job4j.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * EmailNotification.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class EmailNotification {

    /**
     * The thread pool.
     */
    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * The method using ExecutorService sends mail.
     * @param user the user.
     */
    public void emailTo(User user) {
        if (user != null) {
            this.pool.submit(() -> {
                String subject = String.format("Notification %s to email %s.", user.getUserName(), user.getEmail());
                String body = String.format("Add a new event to %s", user.getUserName());
                send(subject, body, user.getEmail());
            });
        }
    }

    /**
     * The method sends mail.
     */
    public void send(String subject, String body, String email) {
    }

    /**
     * The method closes the thread pool.
     */
    public void close() {
        this.pool.shutdown();
        while (!this.pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}