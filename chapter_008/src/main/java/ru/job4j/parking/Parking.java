package ru.job4j.parking;

import java.util.List;
import java.util.Map;

/**
 * Parking.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Parking {

    /**
     * The method parks cars.
     * @param car - car.
     * @return true - the car is parked,
     * false - the car isn't parked.
     */
    boolean park(Car car);

    /**
     * The method drives the car out of the parking.
     * @param car - car.
     */
    void driveOut(Car car);

    /**
     * The method returns a map in which
     * the key is a car,
     * the value is a list of parking places
     * where this car is parked.
     * @return a map in which
     * the key is a car,
     * the value is a list of parking places
     * where this car is parked.
     */
    Map<Car, List<Place>> getCars();
}