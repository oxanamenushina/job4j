package ru.job4j.mail;

import java.util.Set;

/**
 * Mails.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Mails {

    /**
     * The method returns user mails with this nickname.
     * @return user mails with this nickname.
     */
    Set<String> getMails();

    /**
     * The method returns mails of this user
     * with different nicknames.
     * @return mails of this user with different nicknames.
     */
    Set<String> getAllMails();
}