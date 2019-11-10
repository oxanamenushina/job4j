package ru.job4j.parking;

import java.util.Set;

/**
 * Place.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Place extends SimplePlace {

    /**
     * The method returns whether the space is free or not.
     * @return true - the place is free,
     * false - the place is taken.
     */
    boolean getIsEmpty();

    /**
     * The method sets whether the space is free or not.
     * @param isEmpty - true - the place is free,
     * false - the place is taken.
     */
    void setIsEmpty(boolean isEmpty);

    /**
     * The method returns neighboring places.
     * @return neighboring places.
     */
    Set<Place> getNeighbors();

    /**
     * The method sets neighboring places.
     * @param neighbors - neighboring places.
     */
    void setNeighbors(Set<Place> neighbors);
}