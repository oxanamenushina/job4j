package ru.job4j.products;

import java.util.List;

/**
 * StorageDecorator.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public abstract class StorageDecorator<T extends Product> implements Storage<T> {

    private Storage storage;

    public StorageDecorator(Storage<T> storage) {
        this.storage = storage;
    }

    @Override
    public boolean checkCondition(T product) {
        return this.storage.checkCondition(product);
    }

    @Override
    public void put(T product) {
        this.storage.put(product);
    }

    @Override
    public List<T> getProducts() {
        return this.storage.getProducts();
    }

    @Override
    public List<T> removeProducts() {
        return this.storage.removeProducts();
    }
}