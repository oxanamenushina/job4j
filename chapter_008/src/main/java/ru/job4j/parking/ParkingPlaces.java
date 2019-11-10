package ru.job4j.parking;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ParkingPlaces.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ParkingPlaces implements Places {

    private List<Place> places;

    public ParkingPlaces(List<Place> places) {
        this.places = places;
    }

    @Override
    public List<Place> getFreePlaces() {
        return this.places.stream().filter(Place::getIsEmpty).collect(Collectors.toList());
    }

    @Override
    public void takePlaces(List<Place> pls) {
        this.places.stream().filter(pls::contains).forEach(p -> p.setIsEmpty(false));
    }

    @Override
    public void freePlaces(List<Place> pls) {
        this.places.stream().filter(pls::contains).forEach(p -> p.setIsEmpty(true));
    }
}