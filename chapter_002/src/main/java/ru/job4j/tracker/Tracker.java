package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Tracker.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 */
public class Tracker implements ITracker {
    private final List<Item> items = new ArrayList<>();
    private int position = 0;

    /**
     * Метод, реализаущий добавление заявки в хранилище.
     * @param item новая заявка.
     * @return заявка.
     */
    @Override
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(this.position++, item);
        return item;
    }

    /**
     * Метод заменяет заявку с заданным идентификатором в массиве.
     * @param id Идентификатор заменяемой заявки.
     * @param item Новая заявка.
     * @return true - заявка заменена, false - нет.
     */
    @Override
    public boolean replace(String id, Item item) {
        final int count = IntStream.range(0, items.size()).filter(i -> this.items.get(i).getId().equals(id)).findAny().orElse(-1);
        boolean result = false;
        if (count >= 0) {
            this.items.set(count, item);
            item.setId(id);
            result = true;
        }
        return result;
    }

    /**
     * Метод находит заявку по идентификатору в массиве и удаляет ее.
     * @param id Идентификатор удаляемой заявки.
     * @return true - заявка удалена, false - нет.
     */
    @Override
    public boolean delete(String id) {
        boolean result = this.items.removeIf(item -> item.getId().equals(id));
        if (result) {
            this.position--;
        }
        return result;
    }

    /**
     * Метод возвращает копию массива без null.
     * @return массив без null.
     */
    @Override
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Метод поиска заявок с совпадающим именем.
     * @param key Имя искомых заявок.
     * @return Массив заявок с совпадающим именем.
     */
    @Override
    public List<Item> findByName(String key) {
        return items.stream().filter(item -> item.getName().equals(key)).collect(Collectors.toList());
    }

    /**
     * Метод поиска заявки по идентификатору.
     * @param id Идентификатор искомой заявки.
     * @return Найденный Item или null.
     */
    @Override
    public Item findById(String id) {
        return items.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return Integer.toString(position) + System.currentTimeMillis();
    }
}