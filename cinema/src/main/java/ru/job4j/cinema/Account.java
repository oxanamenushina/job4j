package ru.job4j.cinema;

import java.util.Objects;

/**
 * Account.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Account {

    /**
     * A buyer's name.
     */
    private String name;

    /**
     * A buyer's phone.
     */
    private String phoneNumber;

    /**
     * A place.
     */
    private Place place;

    public Account(String name, String phoneNumber, Place place) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.place = place;
    }

    /**
     * The method returns a buyer's name.
     * @return a buyer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * The method sets a buyer's name.
     * @param name a buyer's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The method returns a buyer's phone.
     * @return a buyer's phone.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * The method returns a buyer's phone.
     * @param phoneNumber a buyer's phone.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * The method returns a place.
     * @return a place.
     */
    public Place getPlace() {
        return place;
    }

    /**
     * The method returns a place.
     * @param place a place.
     */
    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(name, account.name)
                && Objects.equals(phoneNumber, account.phoneNumber)
                && Objects.equals(place, account.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber, place);
    }
}