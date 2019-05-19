package ru.job4j.set;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SimpleSetTest {

    @Test
    public void whenAddThreeElementsThenSimpleSetHasTwoElements() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("first");
        set.add("second");
        set.add("first");
        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("second"));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenAddFourElementsThenSimpleSetHasThreeElements() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(5);
        set.add(null);
        set.add(7);
        set.add(null);
        Integer[] expected = {5, null, 7};
        int index = 0;
        for (Integer result : set) {
            assertThat(result, is(expected[index++]));
        }
    }

    @Test
    public void forEachShouldReturnThreeElements() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("first");
        set.add("second");
        set.add("third");
        set.add("second");
        String[] expected = new String[] {"first", "second", "third"};
        int index = 0;
        for (String result : set) {
            assertThat(result, is(expected[index++]));
        }
    }

    @Test
    public void iteratorShouldReturnThreeElements() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("first");
        set.add("second");
        set.add("third");
        set.add("first");
        Iterator<String> it = set.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("first"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("second"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("third"));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void invocationOfIteratorShouldThrowConcurrentModificationException() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("first");
        set.add("second");
        Iterator<String> it = set.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("first"));
        set.add("third");
        it.next();
    }
}