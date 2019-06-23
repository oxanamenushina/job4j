package ru.job4j.search;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class IncorrectDataException extends RuntimeException {

    public IncorrectDataException(String str) {
        System.out.println(str);
    }
}
