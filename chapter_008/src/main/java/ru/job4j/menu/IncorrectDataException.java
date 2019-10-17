package ru.job4j.menu;

/**
 * IncorrectDataException.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class IncorrectDataException extends RuntimeException {

    public IncorrectDataException() {
    }

    public IncorrectDataException(String info) {
        System.out.println(info);
    }
}
