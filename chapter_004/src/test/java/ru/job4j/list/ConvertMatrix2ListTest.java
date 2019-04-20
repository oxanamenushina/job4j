package ru.job4j.list;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @version $Id$
 * @since 0.1
 */
public class ConvertMatrix2ListTest {
    @Test
    public void when2on2ArrayThenList4() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 2},
                {3, 4}
        };
        List<Integer> result = list.toList(input);
        assertThat(result, is(List.of(1, 2, 3, 4)));
    }

    @Test
    public void when2on2and1on3ArrayThenList7() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 2},
                {3, 4},
                {5, 6, 7}
        };
        List<Integer> result = list.toList(input);
        assertThat(result, is(List.of(1, 2, 3, 4, 5, 6, 7)));
    }
}
