package ru.job4j.parking;

/**
 * Lorry.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Lorry extends BaseCar {

    public Lorry(String number, int count) {
        super(number, "L" + count);
    }
}