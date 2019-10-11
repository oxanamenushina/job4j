package ru.job4j.products;

/**
 * Shop.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Shop<T extends Product> extends BaseStorage<T> {

    @Override
    public boolean checkCondition(T product) {
        double percent = this.calculatePercent(product.getCreateDate(), product.getExpirationDate());
        if (percent > 0.75 && percent < 1) {
            product.setIsDiscount(true);
        }
        return percent >= 0.25 && percent < 1;
    }
}