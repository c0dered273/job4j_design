package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean b = false;
        byte by = 127;
        short sh = 16000;
        int i = 0;
        long l = 111;
        float f = 1.1f;
        double d = 32565.1d;
        char ch = 'J';
        LOG.debug("bool: {}, byte: {}, short: {}, int: {}, long: {}, float: {}, double: {}, char: {}", b, by, sh, i, l, f, d, ch);

    }
}
