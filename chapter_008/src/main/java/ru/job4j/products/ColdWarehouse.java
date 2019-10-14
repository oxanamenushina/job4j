package ru.job4j.products;

/**
 * ColdWarehouse.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ColdWarehouse extends Warehouse<CRProduct> {

    @Override
    public boolean checkCondition(CRProduct product) {
        return super.checkCondition(product) && product.getIsVegetable();
    }
}