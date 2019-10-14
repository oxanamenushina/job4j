package ru.job4j.products;

/**
 * Coolability.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Coolability {

    /**
     * The method determines whether he product is to be cooled.
     * @return true - the product is a vegetable and should be cooled,
     * false - the product isn't a vegetable and shouldn't be cooled.
     */
    boolean getIsVegetable();
}