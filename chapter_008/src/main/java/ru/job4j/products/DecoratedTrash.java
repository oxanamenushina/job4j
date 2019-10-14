package ru.job4j.products;

/**
 * DecoratedTrash.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class DecoratedTrash extends StorageDecorator<CRProduct> {

    public DecoratedTrash(Storage<CRProduct> storage) {
        super(storage);
    }

    @Override
    public boolean checkCondition(CRProduct product) {
        return super.checkCondition(product) && !product.getIsRecycling();
    }
}