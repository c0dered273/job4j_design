package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class SimpleSetTest {
    @Test(expected = NoSuchElementException.class)
    public void whenAddItem() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("one");
        set.add("two");
        set.add("one");
        Iterator<String> it = set.iterator();
        assertThat(it.next(), is("one"));
        assertThat(it.next(), is("two"));
        assertThat(it.next(), not("one"));
    }
}