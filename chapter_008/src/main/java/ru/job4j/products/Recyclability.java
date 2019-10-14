package ru.job4j.products;

/**
 * Recyclability.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Recyclability {

    /**
     * The method determines whether
     * the product is recyclable.
     * @return true - the product is recyclable,
     * false - the product isn't recyclable.
     */
    boolean getIsRecycling();
}