package ru.job4j.cache;

/**
 * OptimisticException.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class OptimisticException extends RuntimeException {

    public OptimisticException() {
    }

    public OptimisticException(String s) {
        super(s);
    }
}