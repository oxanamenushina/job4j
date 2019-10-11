package ru.job4j.products;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * BaseStorage.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public abstract class BaseStorage<T extends Product> implements Storage<T> {

    private List<T> products = new LinkedList<>();

    /**
     * The method calculates the percentage
     * of expiration of the product.
     * @param createDate - the product release date.
     * @param expirationDate - expiration date of the product.
     * @return the percentage of expiration of the product.
     */
    public double calculatePercent(LocalDate createDate, LocalDate expirationDate) {
        LocalDate today = LocalDate.now();
        long term = DAYS.between(createDate, expirationDate);
        long pastDays = DAYS.between(createDate, today);
        return (double) pastDays / term;
    }

    @Override
    public void put(T product) {
        this.products.add(product);
    }

    @Override
    public List<T> getProducts() {
        return this.products;
    }
}