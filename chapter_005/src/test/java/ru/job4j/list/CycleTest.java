package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class CycleTest {

    @Test
    public void whenListOfFourElementsHasCycleThenTrue() {
        Cycle<Integer> cycle = new Cycle<>();
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;
        assertThat(cycle.hasCycle(first), is(true));
    }

    @Test
    public void whenListOfFourElementsHasNotCycleThenFalse() {
        Cycle<Integer> cycle = new Cycle<>();
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        first.next = second;
        second.next = third;
        third.next = fourth;
        assertThat(cycle.hasCycle(first), is(false));
    }

    @Test
    public void whenListOfFiveElementsHasNotCycleThenFalse() {
        Cycle<Integer> cycle = new Cycle<>();
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        Node<Integer> fifth = new Node<>(5);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        assertThat(cycle.hasCycle(first), is(false));
    }

    @Test
    public void whenListOfFiveElementsHasCycleThenTrue() {
        Cycle<Integer> cycle = new Cycle<>();
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        Node<Integer> fourth = new Node<>(4);
        Node<Integer> fifth = new Node<>(5);
        first.next = second;
        second.next = third;
        third.next = second;
        fourth.next = fifth;
        assertThat(cycle.hasCycle(first), is(true));
    }
}
