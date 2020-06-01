package ru.job4j.cinema;

import java.util.List;

/**
 * ValidateService.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ValidateService implements Validate {

    /**
     * An instance of ValidateService class.
     */
    private static final Validate VALIDATE = new ValidateService();

    /**
     * An instance of CinemaStorage class.
     */
    private final Storage storage = CinemaStorage.getInstance();

    private ValidateService() {
    }

    /**
     * The method returns an instance of ValidateService.
     * @return an instance of CinemaStorage.
     */
    public static Validate getInstance() {
        return VALIDATE;
    }

    /**
     * The method checks the input for the add account operation.
     * @param account an account to add.
     * @return true - the account added to the storage.
     */
    @Override
    public boolean addAccount(Account account) {
        boolean result = !this.isOccupied(account.getPlace());
        if (result) {
            this.storage.addAccount(account);
        }
        return result;
    }

    /**
     * The method returns a list of places.
     * @return a list of places.
     */
    @Override
    public List<Place> getPlaces() {
        return this.storage.getPlaces();
    }

    /**
     * The method checks if the given place is occupied.
     * @param place a place.
     * @return true - the place is occupied.
     */
    private boolean isOccupied(Place place) {
        return this.storage.getPlaces().stream().anyMatch(p -> p.getRow() == place.getRow()
                && p.getPlaceNumber() == place.getPlaceNumber() && p.getOccupied());
    }
}