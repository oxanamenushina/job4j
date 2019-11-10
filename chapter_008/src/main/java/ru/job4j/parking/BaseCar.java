package ru.job4j.parking;

import java.util.Objects;

/**
 * BaseCar.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public abstract class BaseCar implements Car {

    private String number;
    private String size;

    public BaseCar(String number, String size) {
        this.number = number;
        this.size = size;
    }

    @Override
    public String getNumber() {
        return this.number;
    }

    @Override
    public String getSize() {
        return this.size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseCar baseCar = (BaseCar) o;
        return Objects.equals(number, baseCar.number) && Objects.equals(size, baseCar.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, size);
    }
}
