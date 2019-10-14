package ru.job4j.products;

/**
 * DecoratedWarehouse.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class DecoratedWarehouse extends StorageDecorator<CRProduct> {

    public DecoratedWarehouse(Storage<CRProduct> storage) {
        super(storage);
    }

    @Override
    public boolean checkCondition(CRProduct product) {
        return super.checkCondition(product) && !product.getIsVegetable();
    }
}