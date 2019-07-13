package ru.job4j.algorithms;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class RobotTest {

    @Test
    public void whenSx1Sy0Fx1Fy1ThenPathLength2() {
        int[][] board = {
                {0, 1},
                {0, 1}
        };
        int result = new Robot().minWay(board, 1, 0, 1, 1);
        assertThat(result, is(2));
    }

    @Test(expected = NoWayException.class)
    public void whenSx0Sy0Fx1Fy1ThenThrowNoWayException() {
        int[][] board = {
                {1, 0},
                {0, 1}
        };
        int result = new Robot().minWay(board, 0, 0, 1, 1);
    }

    @Test
    public void whenSx1Sy0Fx2Fy2ThenPathLength4() {
        int[][] board = {
                {1, 1, 0},
                {0, 1, 0},
                {0, 1, 1},
        };
        int result = new Robot().minWay(board, 1, 0, 2, 2);
        assertThat(result, is(4));
    }

    @Test
    public void whenSx2Sy0Fx2Fy4ThenPathLength7() {
        int[][] board = {
                {1, 1, 1, 1, 1},
                {0, 1, 0, 0, 1},
                {0, 1, 1, 0, 1},
                {0, 1, 0, 0, 1},
                {0, 1, 1, 1, 1}
        };
        int result = new Robot().minWay(board, 2, 0, 2, 4);
        assertThat(result, is(7));
    }

    @Test
    public void whenSx3Sy2Fx2Fy0ThenPathLength6() {
        int[][] board = {
                {1, 1, 1, 1, 1, 1},
                {0, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1},
                {0, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1}
        };
        int result = new Robot().minWay(board, 3, 2, 2, 0);
        assertThat(result, is(6));
    }

    @Test(expected = NoWayException.class)
    public void whenSx3Sy4Fx0Fy0ThenThrowNoWayException() {
        int[][] board = {
                {1, 0, 1, 1, 1, 1},
                {0, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1},
                {0, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1}
        };
        int result = new Robot().minWay(board, 3, 4, 0, 0);
    }
}
