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
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        return index < numbers.length && !(IntStream.range(index, numbers.length)
                .dropWhile(n -> numbers[n] % 2 != 0).findFirst().orElse(-1) == -1);
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (!hasNext()) {
           throw new NoSuchElementException();
        }
        index = IntStream.range(index, numbers.length).dropWhile(n -> numbers[n] % 2 != 0).findFirst().getAsInt();
        return numbers[index++];
    }
}
