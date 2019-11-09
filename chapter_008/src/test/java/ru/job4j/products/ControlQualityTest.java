package ru.job4j.products;

import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
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

    @Test
    public void whenDirect1ProductThenThisProductDirectedToTheColdWarehouse() {
        Storage<CRProduct> warehouse = new DecoratedSizeCheck<>(new DecoratedWarehouse(new Warehouse<>()), 1);
        Storage<CRProduct> shop = new Shop<>();
        Storage<CRProduct> trash = new DecoratedTrash(new Trash<>());
        Storage<CRProduct> cw = new ColdWarehouse();
        Storage<CRProduct> sw = new SecondWarehouse((SizeCheck) warehouse);
        Storage<CRProduct> recycling = new Recycling();
        LocalDate today = LocalDate.now();
        ControlQuality<CRProduct> cq = new ControlQuality<>(List.of(trash, warehouse, shop, cw, sw, recycling));
        CRProduct first = new CRFood(new Food("Tomatoes", today.minusDays(2), today.plusDays(12), 129.9, 0.15), true, false);
        cq.directAllProducts(List.of(first));
        assertThat(cw.getProducts(), is(List.of(first)));
    }

    @Test
    public void whenDirect1ProductThenThisProductDirectedToTheRecycling() {
        Storage<CRProduct> warehouse = new DecoratedSizeCheck<>(new DecoratedWarehouse(new Warehouse<>()), 1);
        Storage<CRProduct> shop = new Shop<>();
        Storage<CRProduct> trash = new DecoratedTrash(new Trash<>());
        Storage<CRProduct> cw = new ColdWarehouse();
        Storage<CRProduct> sw = new SecondWarehouse((SizeCheck) warehouse);
        Storage<CRProduct> recycling = new Recycling();
        LocalDate today = LocalDate.now();
        ControlQuality<CRProduct> cq = new ControlQuality<>(List.of(trash, warehouse, shop, cw, sw, recycling));
        CRProduct first = new CRFood(new Food("Chocolate", today.minusDays(367), today.minusDays(2), 88.9, 0.25), false, true);
        cq.directAllProducts(List.of(first));
        assertThat(recycling.getProducts(), is(List.of(first)));
    }

    @Test
    public void whenDirect2ProductsThenFirstDirectedToTheWarehouseSecondDirectedToTheSecondWarehouse() {
        Storage<CRProduct> warehouse = new DecoratedSizeCheck<>(new DecoratedWarehouse(new Warehouse<>()), 1);
        Storage<CRProduct> shop = new Shop<>();
        Storage<CRProduct> trash = new DecoratedTrash(new Trash<>());
        Storage<CRProduct> cw = new ColdWarehouse();
        Storage<CRProduct> sw = new SecondWarehouse((SizeCheck) warehouse);
        Storage<CRProduct> recycling = new Recycling();
        LocalDate today = LocalDate.now();
        ControlQuality<CRProduct> cq = new ControlQuality<>(List.of(trash, warehouse, shop, cw, sw, recycling));
        CRProduct first = new CRFood(new Food("Chocolate", today.minusDays(8), today.plusDays(357), 88.9, 0.25), false, true);
        CRProduct second = new CRFood(new Food("Cookie", today.minusDays(4), today.plusDays(26), 250, 0.15), false, true);
        cq.directAllProducts(List.of(first, second));
        assertThat(warehouse.getProducts(), is(List.of(first)));
        assertThat(sw.getProducts(), is(List.of(second)));
    }

    @Test
    public void whenDirect7ProductsThenTheyAreDirectedToDifferentStorages() {
        Storage<CRProduct> warehouse = new DecoratedSizeCheck<>(new DecoratedWarehouse(new Warehouse<>()), 1);
        Storage<CRProduct> shop = new Shop<>();
        Storage<CRProduct> trash = new DecoratedTrash(new Trash<>());
        Storage<CRProduct> cw = new ColdWarehouse();
        Storage<CRProduct> sw = new SecondWarehouse((SizeCheck) warehouse);
        Storage<CRProduct> recycling = new Recycling();
        LocalDate today = LocalDate.now();
        ControlQuality<CRProduct> cq = new ControlQuality<>(List.of(trash, warehouse, shop, cw, sw, recycling));
        CRProduct first = new CRFood(new Food("Cake", today.minusDays(3), today.plusDays(4), 555.5, 0.3), false, false);
        CRProduct second = new CRFood(new Food("Coffee", today.minusDays(15), today.plusDays(350), 383.9, 0.3), false, true);
        CRProduct third = new CRFood(new Food("Cheese", today.minusDays(10), today.plusDays(80), 580, 0.25), false, false);
        CRProduct fourth = new CRFood(new Food("Ice cream", today.minusDays(95), today.minusDays(5), 78.9, 0.15), false, false);
        CRProduct fifth = new CRFood(new Food("Milk", today.minusDays(10), today.plusDays(2), 60, 0.15), false, false);
        CRProduct sixth = new CRFood(new Food("Cacao", today.minusDays(368), today.minusDays(3), 115.5, 0.25), false, true);
        CRProduct seventh = new CRFood(new Food("Salad", today.minusDays(1), today.plusDays(6), 120, 0.20), true, false);
        cq.directAllProducts(List.of(first, second, third, fourth, fifth, sixth, seventh));
        assertThat(warehouse.getProducts(), is(List.of(second)));
        assertThat(shop.getProducts(), is(List.of(first, fifth)));
        assertThat(trash.getProducts(), is(List.of(fourth)));
        assertThat(cw.getProducts(), is(List.of(seventh)));
        assertThat(sw.getProducts(), is(List.of(third)));
        assertThat(recycling.getProducts(), is(List.of(sixth)));
        assertThat(first.getTotalPrice(), is(555.5));
        assertThat(fifth.getTotalPrice(), is(51.0));
    }

    @Test
    public void  whenResortThenAllProductsAreRedistributedAgain() {
        Storage<Product> warehouse = new Warehouse<>();
        Storage<Product> shop = new Shop<>();
        Storage<Product> trash = new Trash<>();
        LocalDate today = LocalDate.now();
        Product first = new Food("Cake", today.minusDays(3), today.plusDays(4), 555.5, 0.3);
        Product second = new Food("Coffee", today.minusDays(15), today.plusDays(350), 383.9, 0.2);
        Product third = new Food("Cheese", today.minusDays(25), today.plusDays(5), 580, 0.25);
        Product fourth = new Food("Ice cream", today.minusDays(95), today.minusDays(5), 78.9, 0.15);
        ControlQuality<Product> cq = new ControlQuality<>(List.of(trash, warehouse, shop));
        warehouse.put(first);
        shop.put(second);
        trash.put(third);
        trash.put(fourth);
        cq.resort();
        assertThat(warehouse.getProducts(), is(List.of(second)));
        assertThat(shop.getProducts(), containsInAnyOrder(first, third));
        assertThat(trash.getProducts(), is(List.of(fourth)));
    }
}