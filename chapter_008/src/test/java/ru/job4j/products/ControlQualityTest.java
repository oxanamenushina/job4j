package ru.job4j.products;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * ControlQualityTest.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ControlQualityTest {

    @Test
    public void whenDirect1ProductThenThisProductDirectedToTheWarehouse() {
        Storage<Product> warehouse = new Warehouse<>();
        Storage<Product> shop = new Shop<>();
        Storage<Product> trash = new Trash<>();
        LocalDate today = LocalDate.now();
        ControlQuality<Product> cq = new ControlQuality<>(List.of(trash, warehouse, shop));
        Product first = new Food("Chocolate", today.minusDays(8), today.plusDays(357), 88.9, 0.2);
        cq.directAllProducts(List.of(first));
        assertThat(warehouse.getProducts(), is(List.of(first)));
    }

    @Test
    public void whenDirect1ProductThenThisProductDirectedToTheTrash() {
        Storage<Product> warehouse = new Warehouse<>();
        Storage<Product> shop = new Shop<>();
        Storage<Product> trash = new Trash<>();
        LocalDate today = LocalDate.now();
        ControlQuality<Product> cq = new ControlQuality<>(List.of(trash, warehouse, shop));
        Product first = new Food("Fish", today.minusDays(15), today.minusDays(1), 445.5, 0.25);
        cq.directAllProducts(List.of(first));
        assertThat(trash.getProducts(), is(List.of(first)));
    }

    @Test
    public void whenDirect1ProductThenThisProductDirectedToTheShopWithoutDiscount() {
        Storage<Product> warehouse = new Warehouse<>();
        Storage<Product> shop = new Shop<>();
        Storage<Product> trash = new Trash<>();
        LocalDate today = LocalDate.now();
        ControlQuality<Product> cq = new ControlQuality<>(List.of(trash, warehouse, shop));
        Product first = new Food("Juice", today.minusDays(85), today.plusDays(95), 82, 0.15);
        cq.directAllProducts(List.of(first));
        assertThat(shop.getProducts(), is(List.of(first)));
        assertThat(first.getTotalPrice(), is(82.0));
    }

    @Test
    public void whenDirect1ProductThenThisProductDirectedToTheShopWithDiscount() {
        Storage<Product> warehouse = new Warehouse<>();
        Storage<Product> shop = new Shop<>();
        Storage<Product> trash = new Trash<>();
        LocalDate today = LocalDate.now();
        ControlQuality<Product> cq = new ControlQuality<>(List.of(trash, warehouse, shop));
        Product first = new Food("Butter", today.minusDays(82), today.plusDays(8), 100, 0.20);
        cq.directAllProducts(List.of(first));
        assertThat(shop.getProducts(), is(List.of(first)));
        assertThat(first.getTotalPrice(), is(80.0));
    }

    @Test
    public void  whenDirect4ProductsThenOneDirectedToTheWarehouseTwoDirectedToTheShopOneDirectedToTheTrash() {
        Storage<Product> warehouse = new Warehouse<>();
        Storage<Product> shop = new Shop<>();
        Storage<Product> trash = new Trash<>();
        LocalDate today = LocalDate.now();
        ControlQuality<Product> cq = new ControlQuality<>(List.of(trash, warehouse, shop));
        Product first = new Food("Cake", today.minusDays(3), today.plusDays(4), 555.5, 0.3);
        Product second = new Food("Coffee", today.minusDays(15), today.plusDays(350), 383.9, 0.2);
        Product third = new Food("Cheese", today.minusDays(25), today.plusDays(5), 580, 0.25);
        Product fourth = new Food("Ice cream", today.minusDays(95), today.minusDays(5), 78.9, 0.15);
        cq.directAllProducts(List.of(first, second, third, fourth));
        assertThat(warehouse.getProducts(), is(List.of(second)));
        assertThat(shop.getProducts(), is(List.of(first, third)));
        assertThat(trash.getProducts(), is(List.of(fourth)));
        assertThat(first.getTotalPrice(), is(555.5));
        assertThat(third.getTotalPrice(), is(435.0));
    }
}