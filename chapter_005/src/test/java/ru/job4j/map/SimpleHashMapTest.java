package ru.job4j.map;

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
public class SimpleHashMapTest {

    @Test
    public void whenInsertTwoElementsThenSimpleHashMapHasTwoElements() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "A");
        map.insert(2, "B");
        assertThat(map.get(1), is("A"));
        assertThat(map.get(2), is("B"));
    }

    @Test
    public void whenInsertElementWithSameKeyThenNewElementReplacesOld() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "A");
        map.insert(2, "B");
        map.insert(1, "C");
        assertThat(map.get(1), is("C"));
        assertThat(map.get(2), is("B"));
    }

    @Test
    public void whenInsertFiveElementsThenArraySizeIncreases() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "A");
        map.insert(2, "B");
        map.insert(3, "C");
        map.insert(4, "D");
        map.insert(5, "E");
        assertThat(map.get(1), is("A"));
        assertThat(map.get(2), is("B"));
        assertThat(map.get(3), is("C"));
        assertThat(map.get(4), is("D"));
        assertThat(map.get(5), is("E"));
    }

    @Test
    public void whenGetThenReturnValueOfThisKey() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "A");
        map.insert(8, "O");
        assertThat(map.get(1), is("A"));
        assertThat(map.get(8), is("O"));
    }

    @Test
    public void whenDeleteThenThisElementRemove() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "A");
        map.insert(2, "B");
        map.insert(3, "C");
        assertThat(map.get(3), is("C"));
        map.delete(3);
        String expected = null;
        assertThat(map.get(3), is(expected));
    }

    @Test
    public void iteratorShouldReturnThreeElements() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "A");
        map.insert(2, "B");
        map.insert(3, "C");
        Iterator it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(map.getTable()[0]));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(map.getTable()[1]));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(map.getTable()[2]));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void invocationOfIteratorShouldThrowConcurrentModificationException() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(1, "A");
        map.insert(2, "B");
        Iterator it = map.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(map.getTable()[0]));
        map.insert(3, "C");
        it.next();
    }
}
