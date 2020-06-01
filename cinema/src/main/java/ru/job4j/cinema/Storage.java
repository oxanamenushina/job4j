package ru.job4j.cinema;

import java.util.List;

/**
 * Storage.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Storage {

    /**
     * The method adds the account to the storage.
     * @param account an account to add.
     */
    void addAccount(Account account);

    /**
     * The method returns a list of places.
     * @return a list of places.
     */
    List<Place> getPlaces();
}