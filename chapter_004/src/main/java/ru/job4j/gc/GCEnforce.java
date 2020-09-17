package ru.job4j.gc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GCEnforce {
    private static final Logger logger = LoggerFactory.getLogger(GCEnforce.class);

    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
//        System.out.println("Heap size:   " + rt.maxMemory());
        long before = rt.freeMemory();
//        System.out.println("Free memory: " + before);
        for (int i = 0; i < 10; i++) {
            new User();
        }
        long after = rt.freeMemory();
//        System.out.println("Free memory: " + after);
//        System.out.println("Memory consumption: " + (before - after));
    }

    public static class User {
        private String[] bal0 = new String[1 << 14];

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
//            System.out.println("*** User destroyed ***");
        }
    }
}
