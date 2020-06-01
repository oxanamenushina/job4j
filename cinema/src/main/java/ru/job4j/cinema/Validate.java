package ru.job4j.cinema;

import java.util.List;

/**
 * Validate.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Validate {

    /**
     * The method checks the input for the add account operation.
     * @param account an account to add.
     * @return true - the account added to the storage.
     */
    boolean addAccount(Account account);

    /**
     * The method returns a list of places.
     * @return a list of places.
     */
    List<Place> getPlaces();
}