package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicateTest {
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicateNumbers() {
        ArrayDuplicate arrDuplicate = new ArrayDuplicate();
        String[] result = arrDuplicate.remove(new String[]
                {"One", "One", "Two", "Three", "Seven", "One", "Two", "Three", "Seven", "One", "One"});
        String[] expect = {"One", "Two", "Three", "Seven"};
        assertThat(result, arrayContainingInAnyOrder(expect));
    }

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicateWords() {
        ArrayDuplicate arrDuplicate = new ArrayDuplicate();
        String[] result = arrDuplicate.remove(new String[]
                {"Привет", "Мир", "Привет", "Супер", "Мир"});
        String[] expect = {"Привет", "Мир", "Супер"};
        assertThat(result, arrayContainingInAnyOrder(expect));
    }
}
