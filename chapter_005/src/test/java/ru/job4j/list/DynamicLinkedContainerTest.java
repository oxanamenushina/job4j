package ru.job4j.list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DynamicLinkedContainerTest {

    @Test
    public void whenAddThreeElementsThenDynamicLinkedContainerHasThreeElements() {
        DynamicLinkedContainer<String> dynamicList = new DynamicLinkedContainer<>();
        dynamicList.add("first");
        dynamicList.add("second");
        dynamicList.add("third");
        assertThat(dynamicList.get(0), is("third"));
        assertThat(dynamicList.get(1), is("second"));
        assertThat(dynamicList.get(2), is("first"));
    }

    @Test
    public void whenGetFirstElementThenReturnFirstElement() {
        DynamicLinkedContainer<String> dynamicList = new DynamicLinkedContainer<>();
        dynamicList.add("first");
        dynamicList.add("second");
        dynamicList.add("third");
        assertThat(dynamicList.get(1), is("second"));
    }

    @Test
    public void forEachShouldReturnThreeElements() {
        DynamicLinkedContainer<String> dynamicList = new DynamicLinkedContainer<>();
        dynamicList.add("first");
        dynamicList.add("second");
        dynamicList.add("third");
        String[] expected = new String[] {"third", "second", "first"};
        int index = 0;
        for (String result : dynamicList) {
            assertThat(result, is(expected[index++]));
        }
    }

    @Test
    public void iteratorShouldReturnThreeElements() {
        DynamicLinkedContainer<String> dynamicList = new DynamicLinkedContainer<>();
        dynamicList.add("first");
        dynamicList.add("second");
        dynamicList.add("third");
        Iterator<String> it = dynamicList.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("third"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("second"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("first"));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void invocationOfIteratorShouldThrowConcurrentModificationException() {
        DynamicLinkedContainer<String> dynamicList = new DynamicLinkedContainer<>();
        dynamicList.add("first");
        dynamicList.add("second");
        Iterator<String> it = dynamicList.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("second"));
        dynamicList.add("third");
        it.next();
    }
}
