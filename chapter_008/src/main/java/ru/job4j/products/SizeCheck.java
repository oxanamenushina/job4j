package ru.job4j.products;

/**
 * SizeCheck.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface SizeCheck {

    /**
     * The method checks the overflow of the warehouse.
     * @return true - the warehouse is full,
     * false - the warehouse isn't full.
     */
    boolean getIsOverflow();
}