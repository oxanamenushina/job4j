package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class EvenIterator implements Iterator {
    private final int[] numbers;
    private int index = 0;

    public EvenIterator(int[] numbers) {
        this.numbers = IntStream.of(numbers).filter(n -> n % 2 == 0).toArray();
    }

    @Override
    public boolean hasNext() {
        return index < numbers.length;
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (index >= numbers.length) {
           throw new NoSuchElementException();
        }
        return numbers[index++];
    }
}
