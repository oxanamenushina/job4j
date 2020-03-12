package ru.job4j.concurrent;

/**
 * User.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class User {

    /**
     * User name.
     */
    private String userName;

    /**
     * User email.
     */
    private String email;

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    /**
     * The method returns user name.
     * @return user name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * The method returns user email.
     * @return user email.
     */
    public String getEmail() {
        return email;
    }
}