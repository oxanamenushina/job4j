package ru.job4j.products;

/**
 * Warehouse.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Warehouse<T extends Product> extends BaseStorage<T> {

    @Override
    public boolean checkCondition(T product) {
        double percent = this.calculatePercent(product.getCreateDate(), product.getExpirationDate());
        return percent < 0.25;
    }
}