package ru.job4j.cinema;

import java.util.Objects;

/**
 * Place.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Place {

    /**
     * Row number in the hall.
     */
    private int row;

    /**
     * Seat number in a row.
     */
    private int placeNumber;

    /**
     * The price of a seat in the hall.
     */
    private int price;

    /**
     * If the place is occupied.
     */
    private boolean occupied;

    public Place(int row, int placeNumber, int price) {
        this.row = row;
        this.placeNumber = placeNumber;
        this.price = price;
    }

    public Place(int row, int placeNumber, int price, boolean occupied) {
        this.row = row;
        this.placeNumber = placeNumber;
        this.price = price;
        this.occupied = occupied;
    }

    public Place(int row, int placeNumber) {
        this.row = row;
        this.placeNumber = placeNumber;
    }

    public int getRow() {
        return row;
    }

    public int getPrice() {
        return price;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Place place = (Place) o;
        return row == place.row && placeNumber == place.placeNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, placeNumber);
    }
}