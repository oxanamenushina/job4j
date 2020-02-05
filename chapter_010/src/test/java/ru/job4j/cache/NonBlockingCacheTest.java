package ru.job4j.cache;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.core.Is.is;

/**
 * NonBlockingCacheTest.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class NonBlockingCacheTest {

    NonBlockingCache cache = new NonBlockingCache();

    @Before
    public void createCache() {
        this.cache = new NonBlockingCache();
    }


    private void addElements(Base b1, Base b2) throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread t1 = new Thread(
                () -> {
                    try {
                        this.cache.add(b1);
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        Thread t2 = new Thread(
                () -> {
                    try {
                        this.cache.add(b2);
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    @Test
    public void whenAddTwoElementsThenCacheHasTwoElements() throws InterruptedException {
        Base b1 = new Base(1, 0, "1");
        Base b2 = new Base(2, 0, "2");
        this.addElements(b1, b2);
        Assert.assertThat(this.cache.get(1), is(b1));
        Assert.assertThat(this.cache.get(2), is(b2));
    }

    @Test
    public void whenDeleteTwoElementsThenCacheHasNoElements() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Base b1 = new Base(1, 0, "1");
        Base b2 = new Base(2, 0, "2");
        this.addElements(b1, b2);
        Thread t1 = new Thread(
                () -> {
                    try {
                        this.cache.delete(b1);
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        Thread t2 = new Thread(
                () -> {
                    try {
                        this.cache.delete(b2);
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        Assert.assertNull(this.cache.get(1));
        Assert.assertNull(this.cache.get(2));
    }

    @Test
    public void whenUpdateOneElementThenThrowOptimisticException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Base b1 = new Base(1, 0, "1");
        Base b2 = new Base(2, 0, "2");
        this.addElements(b1, b2);
        Thread t1 = new Thread(
                () -> {
                    try {
                        Base b3 = new Base(1, 5, "3");
                        this.cache.update(b3);
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );
        Thread t2 = new Thread(
                () -> {
                    try {
                        b1.setName("11");
                        this.cache.update(b1);
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        Assert.assertThat(ex.get().getMessage(), is("Throw Exception in Thread"));
    }

    @Test
    public void whenUpdateOneElementThenOneElementUpdated() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Base b1 = new Base(1, 0, "1");
        Base b2 = new Base(2, 0, "2");
        this.addElements(b1, b2);
        Thread t1 = new Thread(
                () -> {
                    try {
                        b1.setName("11");
                        this.cache.update(b1);
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );
        Thread t2 = new Thread(
                () -> {
                    try {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        b1.setName("111");
                        this.cache.update(b1);
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        Assert.assertThat(b1.getVersion(), is(2));
    }
}