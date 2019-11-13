package ru.job4j.mail;

import java.util.Set;

/**
 * User.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface User extends SimpleUser, Mails {

    /**
     * The method adds other nicknames of this user.
     * @param users - other nicknames of this user.
     */
    void addUsers(Set<User> users);

    /**
     * The method returns nicknames of this user.
     * @return nicknames of this user.
     */
    Set<User> getAllUsers();
}