package ru.job4j.algorithms;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class NoWayException extends RuntimeException {

    public NoWayException(String s) {
        System.out.println(s);
    }
}