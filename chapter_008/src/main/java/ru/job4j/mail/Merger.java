package ru.job4j.mail;

import java.util.List;

/**
 * Merger.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Merger {

    /**
     * The method merges users.
     * If two users have a common email,
     * then this is the same user.
     * @param users - list of users.
     * @return list of merged users.
     */
    List<User> merge(List<User> users);
}