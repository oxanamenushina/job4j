package ru.job4j.iterator;

import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Array2DIterator implements Iterator {
    private final int[] array;
    private int index = 0;

    public Array2DIterator(int[][] array) {
        this.array = Stream.of(array).flatMapToInt(IntStream :: of).toArray();
    }

    @Override
    public boolean hasNext() {
        return this.index < this.array.length;
    }

    @Override
    public Object next() {
        return array[index++];
    }
}
