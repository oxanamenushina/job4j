package ru.job4j.bank;

import java.util.Objects;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Account {
    private double value;
    private final String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public String getRequisites() {
        return requisites;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else if (obj instanceof Account) {
            Account second = (Account) obj;
            result = (Objects.equals(this.requisites, second.requisites)) && Double.compare(this.value, second.value) == 0;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, requisites);
    }
}
