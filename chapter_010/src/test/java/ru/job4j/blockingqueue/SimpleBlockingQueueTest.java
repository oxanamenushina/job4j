package ru.job4j.blockingqueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * SimpleBlockingQueueTest.
 *
 * @author Oxana Menushina (oxsm@mail.ru).
 * @version $Id$
 * @since 0.1
 */
public class SimpleBlockingQueueTest {

    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    /**
     * Producer.
     */
    public class Producer implements Runnable {

        private SimpleBlockingQueue<Integer> queue;
        private int count;

        public Producer(SimpleBlockingQueue<Integer> queue, int count) {
            this.queue = queue;
            this.count = count;
        }

        @Override
        public void run() {
            IntStream.range(1, this.count + 1).forEach(i -> this.queue.offer(i));
        }
    }

    /**
     * Consumer.
     */
    public class Consumer implements Runnable {

        private SimpleBlockingQueue<Integer> queue;
        private int count;

        public Consumer(SimpleBlockingQueue<Integer> queue, int count) {
            this.queue = queue;
            this.count = count;
        }

        @Override
        public void run() {
            IntStream.range(0, this.count).forEach(i -> this.queue.poll());
        }
    }

    @Test
    public void whenProducerAdd5ElementsThenConsumerGet5Elements() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(new Producer(queue, 5));
        Thread consumer = new Thread(new Consumer(queue, 5));
        consumer.start();
        producer.start();
        producer.join();
        consumer.join();
        assertThat(this.mem.toString(), is(String.format("Producer: 1%nConsumer: 1%nProducer: 2%nConsumer: 2%n"
                + "Producer: 3%nConsumer: 3%nProducer: 4%nConsumer: 4%nProducer: 5%nConsumer: 5%n")));
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> IntStream.range(0, 5).forEach(queue::offer)
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        buffer.add(queue.poll());
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }
}