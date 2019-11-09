package ru.job4j.products;

import java.util.LinkedList;
import java.util.List;

/**
 * ControlQuality.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ControlQuality<T extends Product> {

    private List<Storage> storages;

    public ControlQuality(List<Storage> storage) {
        this.storages = storage;
    }

    /**
     * The method directs the products to different stores.
     * @param products - list of products.
     */
    public void directAllProducts(List<T> products) {
        products.forEach(p -> this.storages.stream().filter(s -> s.checkCondition(p)).forEach(s -> s.put(p)));
    }

    /**
     * The method retrieves all products
     * from the stores and redistributes them again.
     */
    public void resort() {
        List<T> products = new LinkedList<>();
        this.storages.forEach(s -> products.addAll(s.removeProducts()));
        this.directAllProducts(products);
    }
}