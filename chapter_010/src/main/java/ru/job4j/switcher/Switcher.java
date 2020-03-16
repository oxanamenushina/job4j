package ru.job4j.switcher;

import java.util.stream.IntStream;

/**
 * Switcher.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class Switcher {

    private final StringMaker sm = new StringMaker();
    private final Thread t1;
    private final Thread t2;
    private volatile int sign;

    public Switcher(int num1, int num2, int count1, int count2) {
        this.t1 = new Thread(new Performer(count1, num1));
        this.t2 = new Thread(new Performer(count2, num2));
        this.sign = num2;
    }

    private synchronized void addSymbols(int count, int num) {
        if (this.sign == num) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        IntStream.range(0, count).forEach(n -> this.sm.addString(num));
        this.sign = num;
        notify();
    }

    public void start() {
        this.t1.start();
        this.t2.start();
    }

    public void stop() {
        this.t1.interrupt();
        this.t2.interrupt();
        try {
            this.t1.join();
            this.t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public String getResult() {
        return this.sm.getString();
    }

    public class Performer implements Runnable {
        private final int count;
        private final int num;

        public Performer(int count, int num) {
            this.count = count;
            this.num = num;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                addSymbols(this.count, this.num);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public class StringMaker {

        private final StringBuilder sb = new StringBuilder();

        public void addString(int num) {
            this.sb.append(num);
        }

        public String getString() {
            return this.sb.toString();
        }
    }

    public static void main(String[] args) {
        Switcher switcher = new Switcher(1, 2, 10, 10);
        switcher.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        switcher.stop();
        System.out.println(switcher.getResult());
    }
}