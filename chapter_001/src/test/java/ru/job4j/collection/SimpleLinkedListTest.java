package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleLinkedListTest {
    @Test
    public void whenAddThenGet() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("Item1");
        list.add("Item2");
        list.add("Item3");
        String result = list.get(1);
        assertThat(result, is("Item2"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutOfBound() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("Item1");
        list.add("Item2");
        list.add("Item3");
        list.get(3);
    }

    @Test
    public void whenAddThenIt() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("first");
        String rsl = list.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("first");
        Iterator<String> it = list.iterator();
        list.add("second");
        it.next();
    }
}