package ru.job4j.products;

/**
 * Trash.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Trash<T extends Product> extends BaseStorage<T> {

    @Override
    public boolean checkCondition(T product) {
        double percent = this.calculatePercent(product.getCreateDate(), product.getExpirationDate());
        return percent >= 1;
    }
}