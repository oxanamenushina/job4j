package ru.job4j.department;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;

/**
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class DepartmentTest {
    @Test
    public void whenOneAddMissedThenThree() {
        Department department = new Department();
        assertThat(department.addMissed(new String[] {"K1\\SK1\\SSK1"}),
                arrayContainingInAnyOrder("K1", "K1\\SK1", "K1\\SK1\\SSK1")
        );
    }

    @Test
    public void whenTwoAddMissedThenSix() {
        Department department = new Department();
        assertThat(department.addMissed(new String[] {"K1\\SK1\\SSK1", "K2\\SK1\\SSK2"}),
                arrayContainingInAnyOrder("K1", "K1\\SK1", "K1\\SK1\\SSK1", "K2", "K2\\SK1", "K2\\SK1\\SSK2")
        );
    }

    @Test
    public void whenOneAscendingSortThenThreeSorted() {
        Department department = new Department();
        assertThat(department.ascendingSort(new String[] {"K1\\SK1\\SSK2"}),
                is(new String[] {"K1", "K1\\SK1", "K1\\SK1\\SSK2"})
        );
    }

    @Test
    public void whenTwoAscendingSortThenFiveSorted() {
        Department department = new Department();
        assertThat(department.ascendingSort(new String[] {"K1\\SK1\\SSK1", "K2\\SK1"}),
                is(new String[] {"K1", "K1\\SK1", "K1\\SK1\\SSK1", "K2", "K2\\SK1"})
        );
    }

    @Test
    public void whenSevenAscendingSortThenNineSorted() {
        Department department = new Department();
        assertThat(department.ascendingSort(new String[] {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                        "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"}),
                is(new String[] {"K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2",
                        "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"})
        );
    }

    @Test
    public void whenTwoDescendingSortThenThreeSorted() {
        Department department = new Department();
        assertThat(department.descendingSort(new String[] {"K1\\SK1", "K2"}),
                is(new String[] {"K2", "K1", "K1\\SK1"})
        );
    }

    @Test
    public void whenTwoDescendingSortThenFiveSorted() {
        Department department = new Department();
        assertThat(department.descendingSort(new String[] {"K1\\SK1\\SSK1", "K2\\SK1"}),
                is(new String[] {"K2", "K2\\SK1", "K1", "K1\\SK1", "K1\\SK1\\SSK1"})
        );
    }

    @Test
    public void whenSevenDescendingSortThenNineSorted() {
        Department department = new Department();
        assertThat(department.descendingSort(new String[] {"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1",
                        "K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"}),
                is(new String[] {"K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1", "K1\\SK2",
                        "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"})
        );
    }
}
