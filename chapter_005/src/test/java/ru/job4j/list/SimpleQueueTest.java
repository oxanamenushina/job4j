package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SimpleQueueTest {

    @Test
    public void whenPushTwoElementsThenSimpleQueueHasTwoElements() {
        SimpleQueue<String> queue = new SimpleQueue<>();
        queue.push("first");
        queue.push("second");
        assertThat(queue.poll(), is("first"));
        assertThat(queue.poll(), is("second"));
    }

    @Test
    public void whenPollTFourElementsThenElementsReturnAndDeleteInSameOrder() {
        SimpleQueue<String> queue = new SimpleQueue<>();
        queue.push("first");
        queue.push("second");
        queue.push("third");
        queue.push("fourth");
        assertThat(queue.poll(), is("first"));
        assertThat(queue.poll(), is("second"));
        assertThat(queue.poll(), is("third"));
        assertThat(queue.poll(), is("fourth"));
    }
}
