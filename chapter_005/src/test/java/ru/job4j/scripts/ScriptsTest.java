package ru.job4j.scripts;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ScriptsTest {

    Map<Integer, List<Integer>> scr;

    @Before
    public void setUp() {
        scr = new HashMap<>();
        scr.put(1, List.of(2, 3));
        scr.put(2, List.of(4));
        scr.put(3, List.of(4, 5));
        scr.put(4, List.of());
        scr.put(5, List.of());
    }

    @Test
    public void whenScriptId4ThenReturnEmptyList() {
        Scripts scripts = new Scripts();
        assertThat(scripts.load(scr, 4), is(List.of()));
    }

    @Test
    public void whenScriptId3ThenReturn45() {
        Scripts scripts = new Scripts();
        assertThat(scripts.load(scr, 3), is(List.of(4, 5)));
    }

    @Test
    public void whenScriptId1ThenReturn2345() {
        Scripts scripts = new Scripts();
        assertThat(scripts.load(scr, 1), is(List.of(2, 3, 4, 5)));
    }
}
