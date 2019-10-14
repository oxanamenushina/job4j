package ru.job4j.products;

/**
 * Recycling.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Recycling extends Trash<CRProduct> {

    @Override
    public boolean checkCondition(CRProduct product) {
        return super.checkCondition(product) && product.getIsRecycling();
    }
}