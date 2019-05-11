package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SimpleStackTest {

    @Test
    public void whenPushTwoElementsThenSimpleStackHasTwoElements() {
        SimpleStack<String> simpleStack = new SimpleStack<>();
        simpleStack.push("first");
        simpleStack.push("second");
        assertThat(simpleStack.poll(), is("second"));
        assertThat(simpleStack.poll(), is("first"));
    }

    @Test
    public void whenPollThreeElementsThenElementsReturnAndDeleteInReverseOrder() {
        SimpleStack<String> simpleStack = new SimpleStack<>();
        simpleStack.push("first");
        simpleStack.push("second");
        simpleStack.push("third");
        assertThat(simpleStack.poll(), is("third"));
        assertThat(simpleStack.poll(), is("second"));
        assertThat(simpleStack.poll(), is("first"));
    }
}
