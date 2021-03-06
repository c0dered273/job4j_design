package ru.job4j.waitnotify;

import java.util.LinkedList;
import java.util.Queue;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * JavaDoc.
 *
 * @param <T> param
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) {
        queue.offer(value);
        this.notifyAll();
    }

    /**
     * JavaDoc.
     *
     * @return return
     * @throws InterruptedException interrupt
     */
    public synchronized T poll() throws InterruptedException {
        T rsl;
        while ((rsl = queue.poll()) == null) {
            this.wait();
        }
        return rsl;
    }
}