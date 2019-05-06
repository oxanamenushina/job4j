package ru.job4j.generic;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @version $Id$
 * @since 0.1
 */
public class SimpleArrayTest {

    @Test
    public void whenAddStringThenArrayHasThisString() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("first");
        simpleArray.add("second");
        Iterator<String> it = simpleArray.iterator();
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("second"));
    }

    @Test(expected = ArrayOverflowException.class)
    public void invocationOfAddMethodShouldThrowArrayOverflowException() {
        SimpleArray<String> simpleArray = new SimpleArray<>(3);
        simpleArray.add("first");
        simpleArray.add("second");
        simpleArray.add("third");
        simpleArray.add("fourth");
    }

    @Test
    public void whenAddIntegerThenArrayHasThisInteger() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(5);
        simpleArray.add(1);
        simpleArray.add(2);
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenSetNewStringThenArrayChangeString() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("first");
        simpleArray.add("second");
        simpleArray.set(1, "third");
        Iterator<String> it = simpleArray.iterator();
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("third"));
    }

    @Test
    public void whenSetNewIntegerThenArrayChangeIntegerValue() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(5);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.set(0, 3);
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenRemoveStringThenThisStringDeleteFromArray() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("first");
        simpleArray.add("second");
        simpleArray.add("third");
        simpleArray.remove(1);
        Iterator<String> it = simpleArray.iterator();
        assertThat(it.next(), is("first"));
        assertThat(it.next(), is("third"));
    }

    @Test
    public void whenRemoveIntegerThenThisIntegerValueDeleteFromArray() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(5);
        simpleArray.add(1);
        simpleArray.add(2);
        simpleArray.add(3);
        simpleArray.remove(0);
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
    }

    @Test
    public void whenGetThenReturnString() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("first");
        simpleArray.add("second");
        String result = simpleArray.get(1);
        assertThat(result, is("second"));
    }

    @Test
    public void whenGetThenReturnIntegerValue() {
        SimpleArray<Integer> simpleArray = new SimpleArray<>(5);
        simpleArray.add(1);
        simpleArray.add(2);
        Integer result = simpleArray.get(0);
        assertThat(result, is(1));
    }

    @Test
    public void forEachSimpleArray() {
        SimpleArray<String> simpleArray = new SimpleArray<>(3);
        simpleArray.add("first");
        simpleArray.add("second");
        simpleArray.add("third");
        String[] expected = new String[] {"first", "second", "third"};
        int index = 0;
        for (String st : simpleArray) {
            assertThat(st, is(expected[index++]));
        }
    }

}
