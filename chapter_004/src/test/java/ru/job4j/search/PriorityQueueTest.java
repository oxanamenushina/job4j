package ru.job4j.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @version $Id$
 * @since 0.1
 */
public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    @Test
    public void whenHigherPriority1() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 7));
        queue.put(new Task("urgent", 2));
        queue.put(new Task("middle", 3));
        queue.put(new Task("lowest", 8));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }
}