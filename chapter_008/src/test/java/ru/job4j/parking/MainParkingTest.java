package ru.job4j.parking;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * MainParkingTest.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class MainParkingTest {

    @Test
    public void whenOnePassengerCarParksThenOneCarParked() {
        Car car1 = new PassengerCar("o111oo77");
        Place pl1 = new ParkingPlace(1, false);
        Places pls = new ParkingPlaces(List.of(pl1));
        Parking parking = new MainParking(pls);
        parking.park(car1);
        assertThat(parking.getCars(), is(Map.of(car1, List.of(pl1))));
    }

    @Test
    public void whenOneLorryParksThenOneCarParked() {
        Car car1 = new Lorry("o222oo77", 2);
        Place pl1 = new ParkingPlace(1, true);
        Places pls = new ParkingPlaces(List.of(pl1));
        Parking parking = new MainParking(pls);
        parking.park(car1);
        assertThat(parking.getCars(), is(Map.of(car1, List.of(pl1))));
    }

    @Test
    public void whenLorryParksInThreeSmallParkingPlacesThenOneCarParked() {
        Car car1 = new Lorry("o222oo77", 3);
        Place pl1 = new ParkingPlace(1, false);
        Place pl2 = new ParkingPlace(2, false);
        Place pl3 = new ParkingPlace(3, false, Set.of(pl1, pl2));
        Places pls = new ParkingPlaces(List.of(pl1, pl2, pl3));
        Parking parking = new MainParking(pls);
        parking.park(car1);
        assertThat(parking.getCars().keySet(), is(Set.of(car1)));
        assertThat(parking.getCars().entrySet().iterator().next().getValue(), is(List.of(pl1, pl3, pl2)));
    }

    @Test
    public void whenLorryParksInASmallParkingPlaceThenNoCarsParkedInTheParking() {
        Car car1 = new Lorry("o222oo77", 3);
        Place pl1 = new ParkingPlace(1, false);
        Places pls = new ParkingPlaces(List.of(pl1));
        Parking parking = new MainParking(pls);
        parking.park(car1);
        assertThat(parking.getCars(), is(Map.of()));
    }

    @Test
    public void whenPassengerCarParksInALargeParkingPlaceThenNoCarsParkedInTheParking() {
        Car car1 = new PassengerCar("o111oo77");
        Place pl1 = new ParkingPlace(1, true);
        Places pls = new ParkingPlaces(List.of(pl1));
        Parking parking = new MainParking(pls);
        parking.park(car1);
        assertThat(parking.getCars(), is(Map.of()));
    }

    @Test
    public void whenFiveCarsParksThenFourCarsParked() {
        Car car1 = new PassengerCar("o111oo77");
        Car car2 = new Lorry("o222oo77", 2);
        Car car3 = new Lorry("o333oo77", 3);
        Car car4 = new Lorry("o444oo77", 3);
        Car car5 = new PassengerCar("o555oo77");
        Place pl1 = new ParkingPlace(1, false);
        Place pl2 = new ParkingPlace(2, true, Set.of(pl1));
        Place pl3 = new ParkingPlace(4, false, Set.of(pl2));
        Place pl4 = new ParkingPlace(3, true, Set.of(pl3));
        Place p15 = new ParkingPlace(5, false, Set.of(pl4));
        Place pl6 = new ParkingPlace(6, false, Set.of(p15));
        Places pls = new ParkingPlaces(List.of(pl1, pl2, pl3, pl4, p15, pl6));
        Parking parking = new MainParking(pls);
        parking.park(car1);
        parking.park(car2);
        parking.park(car3);
        parking.park(car4);
        parking.park(car5);
        assertThat(parking.getCars().keySet(), containsInAnyOrder(car1, car2, car3, car5));
    }

    @Test
    public void whenOneOfTwoCarsLeavesTheParkingThenOneCarParked() {
        Car car1 = new PassengerCar("o111oo77");
        Car car2 = new Lorry("o222oo77", 2);
        Place pl1 = new ParkingPlace(1, false);
        Place pl2 = new ParkingPlace(2, true, Set.of(pl1));
        Places pls = new ParkingPlaces(List.of(pl1, pl2));
        Parking parking = new MainParking(pls);
        parking.park(car1);
        parking.park(car2);
        parking.driveOut(car1);
        assertThat(parking.getCars().keySet(), is(Set.of(car2)));
    }
}