package ru.job4j.parking;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * ParkingPlace.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ParkingPlace implements Place {

    private int number;
    private Set<Place> neighbors = new HashSet<>();
    private boolean isEmpty = true;
    private String size;

    public ParkingPlace(int number, boolean isLarge) {
        this.number = number;
        this.size = isLarge ? "L" : "S";
    }

    public ParkingPlace(int number, boolean isLarge, Set<Place> neighbors) {
        this(number, isLarge);
        this.neighbors.addAll(neighbors);
        neighbors.forEach(p -> p.setNeighbors(Set.of(this)));
    }

    @Override
    public int getNumber() {
        return this.number;
    }

    @Override
    public Set<Place> getNeighbors() {
        return this.neighbors;
    }

    @Override
    public boolean getIsEmpty() {
        return this.isEmpty;
    }

    @Override
    public void setNeighbors(Set<Place> neighbors) {
        this.neighbors.addAll(neighbors);
    }

    @Override
    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    @Override
    public String getSize() {
        return this.size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ParkingPlace that = (ParkingPlace) o;
        return this.number == that.number && Objects.equals(this.size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.number, this.size);
    }
}