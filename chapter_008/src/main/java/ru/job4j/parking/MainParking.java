package ru.job4j.parking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * MainParking.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class MainParking implements Parking {

    private Map<Car, List<Place>> cars = new HashMap<>();
    private Places places;

    public MainParking(Places places) {
        this.places = places;
    }

    @Override
    public boolean park(Car car) {
        List<Place> pls = this.places.getFreePlaces().stream()
                .filter(p -> car.getSize().startsWith(p.getSize()))
                .collect(Collectors.toList());
        pls = !pls.isEmpty() ? pls.subList(0, 1) : "S".equals(car.getSize()) ? pls : this.findPlaces(car);
        if (!pls.isEmpty()) {
            this.places.takePlaces(pls);
            this.cars.put(car, pls);
        }
        return !pls.isEmpty();
    }

    @Override
    public void driveOut(Car car) {
        if (this.cars.containsKey(car)) {
            this.places.freePlaces(this.cars.remove(car));
        } else {
            throw new NoSuchCarException();
        }
    }

    @Override
    public Map<Car, List<Place>> getCars() {
        return this.cars;
    }

    /**
     * The method searches for small parking places for large cars.
     * @param car - car.
     * @return list of small parking places for large car.
     */
    private List<Place> findPlaces(Car car) {
        List<Place> pls = new ArrayList<>();
        int size = Integer.parseInt(car.getSize().substring(1));
        Set<Place> passed = new HashSet<>();
        int counter = 0;
        while (counter <= this.places.getFreePlaces().size() - 1 && pls.size() < size) {
            Place current = this.places.getFreePlaces().get(counter++);
            if (!passed.contains(current)) {
                pls.add(current);
                pls = this.findNearbyPlaces(current, pls, size, 0, passed);
                pls = pls.size() > size ? pls.subList(0, size) : pls;
                if (pls.size() < size) {
                    pls.clear();
                }
            }
        }
        return pls;
    }

    /**
     * The method searches for nearby small parking places.
     * @param place - parking place.
     * @param pls - list of nearby small parking places.
     * @param size - the number of small parking places
     * occupied by a large car.
     * @param count - counter.
     * @param passed - the checked parking spaces.
     * @return list of nearby small parking places.
     */
    private List<Place> findNearbyPlaces(Place place, List<Place> pls, int size, int count, Set<Place> passed) {
        place.getNeighbors().stream().filter(p -> p.getIsEmpty() && !passed.contains(p)).forEach(pls::add);
        passed.addAll(pls);
        if (pls.size() < size && pls.size() - 1 >= ++count) {
            pls = this.findNearbyPlaces(pls.get(count), pls, size, count, passed);
        }
        return pls;
    }
}