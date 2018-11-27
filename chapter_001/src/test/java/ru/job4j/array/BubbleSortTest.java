package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class BubbleSortTest {
    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        BubbleSort sortArr = new BubbleSort();
        int[] resultArray = sortArr.sort(new int[] {1, 5, 4, 2, 3, 1, 7, 8, 0, 5});
        int[] expectArray = new int[] {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
        assertThat(resultArray, is(expectArray));
    }

    @Test
    public void whenSortArrayWithSevenElementsThenSortedArray() {
        BubbleSort sortArr = new BubbleSort();
        int[] resultArray = sortArr.sort(new int[] {9, 7, 3, 8, 4, 2, 0});
        int[] expectArray = new int[] {0, 2, 3, 4, 7, 8, 9};
        assertThat(resultArray, is(expectArray));
    }
}