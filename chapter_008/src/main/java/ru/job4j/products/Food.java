package ru.job4j.products;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Food.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Food implements Product {

    private String name;
    private LocalDate expirationDate;
    private LocalDate createDate;
    private double price;
    private double discount;
    private boolean isDiscount = false;

    public Food(String name, LocalDate createDate, LocalDate expirationDate, double price, double discount) {
        this.name = name;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    @Override
    public LocalDate getCreateDate() {
        return this.createDate;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public double getTotalPrice() {
        return this.isDiscount ? this.price * (1 - this.discount) : this.price;
    }

    @Override
    public double getDiscount() {
        return this.discount;
    }

    @Override
    public boolean getIsDiscount() {
        return this.isDiscount;
    }

    @Override
    public void setIsDiscount(boolean isDiscount) {
        this.isDiscount = isDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0
                && Double.compare(food.discount, discount) == 0
                && Objects.equals(name, food.name)
                && Objects.equals(expirationDate, food.expirationDate)
                && Objects.equals(createDate, food.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expirationDate, createDate, price, discount);
    }
}