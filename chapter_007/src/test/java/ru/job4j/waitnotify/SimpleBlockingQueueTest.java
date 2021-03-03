package ru.job4j.waitnotify;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleBlockingQueueTest {
    private volatile Integer retVal;

    @Test
    public void whenQueueIsEmpty() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread consumer = new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        consumer.start();
        consumer.join(100);
        assertThat(consumer.getState(), is(Thread.State.WAITING));
    }

    @Test
    public void whenReturnValueFromQueue() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(() -> queue.offer(42));
        Thread consumer = new Thread(() -> {
            try {
                retVal = queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        consumer.start();
        consumer.join(100);
        producer.start();
        producer.join();
        assertThat(retVal, is(42));
    }
}