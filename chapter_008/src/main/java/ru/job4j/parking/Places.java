package ru.job4j.parking;

import java.util.List;

/**
 * Places.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Places {

    /**
     * The method returns free places.
     * @return free places.
     */
    List<Place> getFreePlaces();

    /**
     * The method frees up parking places.
     * @param pls - places.
     */
    void takePlaces(List<Place> pls);

    /**
     * The method takes up parking places.
     * @param pls - places.
     */
    void freePlaces(List<Place> pls);
}