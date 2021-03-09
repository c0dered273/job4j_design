package ru.job4j.pool;

import java.util.LinkedList;
import java.util.List;
import ru.job4j.waitnotify.SimpleBlockingQueue;

/**
 * Simple thread pool.
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();

    /**
     * Creates threads for a number of available processors.
     */
    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            Thread newThread = new Thread(new Pool(tasks));
            threads.add(newThread);
            newThread.start();
        }
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    private static class Pool implements Runnable {
        private final SimpleBlockingQueue<Runnable> tasks;

        public Pool(SimpleBlockingQueue<Runnable> tasks) {
            this.tasks = tasks;
        }

        @Override
        public void run() {
            for (;;) {
                try {
                    tasks.poll().run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
