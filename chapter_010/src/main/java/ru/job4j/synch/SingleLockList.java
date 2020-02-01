package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DynamicList;

import java.util.Iterator;

/**
 * SingleLockList.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {

    /**
     * DynamicList<T> object.
     */
    @GuardedBy("this")
    private DynamicList<T> list = new DynamicList<>();

    /**
     * The method adds the value to the list.
     * @param value - the value to add.
     */
    public synchronized void add(T value) {
        this.list.add(value);
    }

    /**
     * The method returns the value from the list by index.
     * @return value.
     */
    public synchronized T get(int index) {
        return this.list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }

    /**
     * The method copies all values ​​
     * from one object DynamicList<T> to another.
     * @param array DynamicList<T> object.
     * @return new object DynamicList<T> with copied values.
     */
    private DynamicList<T> copy(DynamicList<T> array) {
        DynamicList<T> arr = new DynamicList<>();
        for (T elem : array) {
            arr.add(elem);
        }
        return arr;
    }
}