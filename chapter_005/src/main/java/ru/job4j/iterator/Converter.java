package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Converter {

    /**
     * Метод принимает объект Итератор итераторов и возвращатет Итератор чисел.
     * @param it паспорт пользователя
     * @return Итератор чисел
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            private Iterator<Integer> inner = it.next();

            @Override
            public boolean hasNext() {
                boolean result = inner.hasNext();
                while (!result && it.hasNext()) {
                    inner = it.next();
                    if (inner.hasNext()) {
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return inner.next();
            }
        };
    }
}
