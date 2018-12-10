package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test ThreeArrays.
 */
public class ThreeArraysTest {
    @Test
    public void whenFirstSortedArrayAddSecondSortedArrayThenThirdSortedArray() {
        int[] array1 = {1, 2, 3, 7, 8, 9};
        int[] array2 = {0, 1, 3, 4, 5, 7, 9};
        ThreeArrays connection = new ThreeArrays();
        int[] expect = {0, 1, 1, 2, 3, 3, 4, 5, 7, 7, 8, 9, 9};
        assertThat(connection.sortedArray(array1, array2), is(expect));
    }
}
