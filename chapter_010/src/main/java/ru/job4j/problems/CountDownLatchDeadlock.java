package ru.job4j.problems;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatchDeadlock.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class CountDownLatchDeadlock {

    private final Thread t1;
    private final Thread t2;

    public CountDownLatchDeadlock() {
        CountDownLatch cdl = new CountDownLatch(5);
        CountD c1 = new CountD(cdl);
        CountD c2 = new CountD(cdl);
        this.t1 = new Thread(new R(c1, c2));
        this.t2 = new Thread(new R(c2, c1));
    }

    public void start() {
        this.t1.start();
        this.t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public class R implements Runnable {
        private final CountD first;
        private final CountD second;

        public R(CountD first, CountD second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public void run() {
            System.out.printf("%s начал свою работу%n", Thread.currentThread().getName());
            first.decr(second);
            System.out.printf("%s заканчивает свою работу%n", Thread.currentThread().getName());
        }
    }

    public class CountD {
        private final CountDownLatch cd;

        public CountD(CountDownLatch cd) {
            this.cd = cd;
        }

        public synchronized void decr(CountD c) {
            System.out.printf("счетчик равен %s%n", this.cd.getCount());
            this.cd.countDown();
            System.out.printf("%s уменьшил значение обратного счетчика на 1, счетчик равен %s%n",
                    Thread.currentThread().getName(), this.cd.getCount());
            c.decr1();
            System.out.printf("%s выполняет метод decr, счетчик равен %s%n",
                    Thread.currentThread().getName(), this.cd.getCount());
            try {
                this.cd.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public synchronized void decr1() {
            this.cd.countDown();
            System.out.printf("%s выполняет метод decr1, счетчик равен %s%n",
                    Thread.currentThread().getName(), this.cd.getCount());
        }
    }

    public static void main(String[] args) {
        CountDownLatchDeadlock countDownLatchDeadlock = new CountDownLatchDeadlock();
        countDownLatchDeadlock.start();
    }
}