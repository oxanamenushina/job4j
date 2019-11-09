package ru.job4j.products;

import java.util.List;

/**
 * Storage.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Storage<T> {

    /**
     * The method checks whether the product
     * meets the storage condition.
     * @param product - product.
     * @return true - the product satisfies the condition,
     * false - the product doesn't satisfies the condition.
     */
    boolean checkCondition(T product);

    /**
     * The method puts the product in storage.
     * @param product - product.
     */
    void put(T product);

    /**
     * The method returns a list
     * of products stored in storage.
     * @return list of products.
     */
    List<T> getProducts();

    /**
     * The method removes all products stored in storage.
     * @return list of products.
     */
    List<T> removeProducts();
}