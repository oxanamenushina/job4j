package ru.job4j.mail;

import java.util.*;

/**
 * MainMail.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class MainMail {

    private List<User> users;
    private Merger merger;

    public MainMail(List<User> users, Merger merger) {
        this.users = users;
        this.merger = merger;
    }

    /**
     * The method starts the application.
     */
    public void start() {
        List<User> total = this.merger.merge(this.users);
        this.print(total);
    }

    /**
     * The method displays a list of merged users
     * and their mails on the console.
     * @param users - list of users.
     */
    private void print(List<User> users) {
        for (User user : users) {
            System.out.println(String.format("%s%s%s", "User ", user.getName(), " has mails:"));
            for (String mail : user.getAllMails()) {
                System.out.print(mail + System.lineSeparator());
            }
        }
    }
}