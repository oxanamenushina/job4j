package ru.job4j.mail;

import java.util.HashSet;
import java.util.Set;

/**
 * MailUser.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class MailUser implements User {

    private String name;
    private Set<String> emails = new HashSet<>();
    private Set<User> others = new HashSet<>();

    public MailUser(String name, Set<String> email) {
        this.name = name;
        this.emails.addAll(email);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<String> getMails() {
        return emails;
    }

    @Override
    public Set<String> getAllMails() {
        this.others.forEach(user -> this.emails.addAll(user.getMails()));
        return this.emails;
    }

    @Override
    public void addUsers(Set<User> us) {
        this.others.addAll(us);
    }

    @Override
    public Set<User> getAllUsers() {
        return this.others;
    }
}