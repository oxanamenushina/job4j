package ru.job4j.list;

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
public class DynamicListTest {

    @Test
    public void whenAddTwoElementsThenDynamicListHasTwoElements() {
        DynamicList<String> dynamicList = new DynamicList<>();
        dynamicList.add("first");
        dynamicList.add("second");
        assertThat(dynamicList.get(1), is("second"));
    }

    @Test
    public void whenAddSixElementsThenDynamicListExpandsAndHasSixElements() {
        DynamicList<String> dynamicList = new DynamicList<>();
        dynamicList.add("first");
        dynamicList.add("second");
        dynamicList.add("third");
        dynamicList.add("fourth");
        dynamicList.add("fifth");
        dynamicList.add("sixth");
        assertThat(dynamicList.get(5), is("sixth"));
    }

    @Test
    public void whenGetFirstElementThenReturnFirstElement() {
        DynamicList<String> dynamicList = new DynamicList<>();
        dynamicList.add("first");
        dynamicList.add("second");
        assertThat(dynamicList.get(1), is("second"));
    }

    @Test
    public void forEachShouldReturnThreeElements() {
        DynamicList<String> dynamicList = new DynamicList<>();
        dynamicList.add("first");
        dynamicList.add("second");
        dynamicList.add("third");
        String[] expected = new String[] {"first", "second", "third"};
        int index = 0;
        for (String result : dynamicList) {
            assertThat(result, is(expected[index++]));
        }
    }

    @Test
    public void iteratorShouldReturnThreeElements() {
        DynamicList<String> dynamicList = new DynamicList<>();
        dynamicList.add("first");
        dynamicList.add("second");
        dynamicList.add("third");
        Iterator<String> it = dynamicList.iterator();
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
        DynamicList<String> dynamicList = new DynamicList<>();
        dynamicList.add("first");
        dynamicList.add("second");
        Iterator<String> it = dynamicList.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("first"));
        dynamicList.add("third");
        it.next();
    }

}