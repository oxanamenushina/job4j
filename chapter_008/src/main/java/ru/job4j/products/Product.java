package ru.job4j.products;

import java.time.LocalDate;

/**
 * Product.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public interface Product {

    /**
     * The method returns the name of the product.
     * @return name of the product.
     */
    String getName();

    /**
     * The method returns expiration date of the product.
     * @return expiration date of the product.
     */
    LocalDate getExpirationDate();

    /**
     * The method returns the product release date.
     * @return the product release date.
     */
    LocalDate getCreateDate();

    /**
     * The method returns the price of the product.
     * @return the price of the product.
     */
    double getPrice();

    /**
     * The method returns the total price
     * of the product with a discount.
     * @return the total price of the product.
     */
    double getTotalPrice();

    /**
     * The method checks whether the product
     * meets the storage condition.
     * @return name of product.
     */
    double getDiscount();

    /**
     * The method returns a discount on the product.
     * @return a discount on the product.
     */
    boolean getIsDiscount();

    /**
     * The method sets if there is a discount on the product.
     * True - the product has a discount,
     * false - the product hasn't a discount.
     * @param isDiscount - true - the product has a discount,
     * false - the product hasn't a discount.
     */
    void setIsDiscount(boolean isDiscount);
}