package ru.job4j.list;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @version $Id$
 * @since 0.1
 */
public class ConvertList2ArrayTest {

    @Test
    public void when6ElementsThen6() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(List.of(1, 2, 3, 4, 5, 6), 3);
        int[][] expect = {
                {1, 2},
                {3, 4},
                {5, 6}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(List.of(1, 2, 3, 4, 5, 6, 7), 3);
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    @Test
    public void when1On4And1On1ArrayThenList5() {
        ConvertList2Array list = new ConvertList2Array();
        List<Integer> result = list.convert(List.of(
                new int[]{1, 2, 3, 4},
                new int[]{5})
        );
        assertThat(result, is(List.of(1, 2, 3, 4, 5)));
    }

    @Test
    public void when1On3And1On2And1On4ArrayThenList9() {
        ConvertList2Array list = new ConvertList2Array();
        List<Integer> result = list.convert(List.of(
                new int[]{1, 2, 3},
                new int[]{4, 5},
                new int[]{6, 7, 8, 9})
        );
        assertThat(result, is(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }
}