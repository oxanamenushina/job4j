package ru.job4j.products;

/**
 * SecondWarehouse.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SecondWarehouse extends Warehouse<CRProduct> {

    private SizeCheck first;

    public SecondWarehouse(SizeCheck first) {
        this.first = first;
    }

    @Override
    public boolean checkCondition(CRProduct product) {
        return super.checkCondition(product) && this.first.getIsOverflow() && !product.getIsVegetable();
    }
}