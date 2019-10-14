package ru.job4j.products;

/**
 * DecoratedSizeCheck.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class DecoratedSizeCheck<T extends Product> extends StorageDecorator<T> implements SizeCheck {

    private int size;
    private boolean isOverflow = false;

    public DecoratedSizeCheck(Storage<T> storage, int size) {
        super(storage);
        this.size = size;
    }

    @Override
    public boolean checkCondition(T product) {
        this.isOverflow = this.getProducts().size() >= this.size;
        return super.checkCondition(product) && !this.isOverflow;
    }

    @Override
    public boolean getIsOverflow() {
        return this.isOverflow;
    }
}