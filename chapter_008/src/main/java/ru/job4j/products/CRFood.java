package ru.job4j.products;

import java.time.LocalDate;

/**
 * CRFood.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class CRFood implements CRProduct {

    private Product product;
    private boolean isVegetable;
    private boolean isRecycling;

    public CRFood(Product product, boolean isVegetable, boolean isRecycling) {
        this.product = product;
        this.isVegetable = isVegetable;
        this.isRecycling = isRecycling;
    }

    @Override
    public String getName() {
        return this.product.getName();
    }

    @Override
    public LocalDate getExpirationDate() {
        return this.product.getExpirationDate();
    }

    @Override
    public LocalDate getCreateDate() {
        return this.product.getCreateDate();
    }

    @Override
    public double getPrice() {
        return this.product.getPrice();
    }

    @Override
    public double getTotalPrice() {
        return this.product.getTotalPrice();
    }

    @Override
    public double getDiscount() {
        return this.product.getDiscount();
    }

    @Override
    public boolean getIsDiscount() {
        return this.product.getIsDiscount();
    }

    @Override
    public void setIsDiscount(boolean isDiscount) {
        this.product.setIsDiscount(isDiscount);
    }

    @Override
    public boolean getIsVegetable() {
        return this.isVegetable;
    }

    @Override
    public boolean getIsRecycling() {
        return this.isRecycling;
    }
}