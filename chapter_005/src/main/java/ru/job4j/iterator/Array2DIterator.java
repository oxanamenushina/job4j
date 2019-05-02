package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Array2DIterator implements Iterator {
    private final int[][] array;
    private int i = 0;
    private int j = 0;

    public Array2DIterator(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return (this.i < this.array.length && this.j < this.array[i].length)
                || (this.i < this.array.length - 1 && this.j == this.array[i].length);
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return this.j < this.array[i].length ? array[i][j++] : array[++i][0];
    }
}
