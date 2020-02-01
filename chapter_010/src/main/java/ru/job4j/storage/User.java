package ru.job4j.storage;

import java.util.Objects;

/**
 * User.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class User {

    /**
     * User ID.
     */
    private int id;

    /**
     * The amount on the user's account.
     */
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * The method returns user ID.
     * @return user ID.
     */
    public int getId() {
        return id;
    }

    /**
     * The method returns the amount on the user's account.
     * @return the amount on the user's account.
     */
    public int getAmount() {
        return amount;
    }

    /**
     * The method sets new amount on the user's account.
     * @param amount new amount on the user's account.
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}