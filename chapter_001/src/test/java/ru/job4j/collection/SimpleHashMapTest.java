package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;


public class SimpleHashMapTest {
    @Test
    public void whenAddItemHasNext() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert(2, "value1");
        simpleHashMap.insert(6, "value2");
        simpleHashMap.insert(15, "value3");
        Iterator<MapEntry<Integer, String>> iterator = simpleHashMap.iterator();
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void whenAddItemThenIter() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert(2, "value1");
        simpleHashMap.insert(6, "value2");
        simpleHashMap.insert(15, "value3");
        Iterator<MapEntry<Integer, String>> iterator = simpleHashMap.iterator();
        assertThat(iterator.next().getKey(), is(2));
        assertThat(iterator.next().getKey(), is(6));
        assertThat(iterator.next().getKey(), is(15));
    }

    @Test
    public void whenAddExist() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert(2, "value1");
        boolean result = simpleHashMap.insert(34, "value3");
        assertThat(result, is(false));
    }

    @Test
    public void whenAddItemThenGet() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert(2, "value1");
        simpleHashMap.insert(6, "value3");
        simpleHashMap.insert(15, "value2");
        assertThat(simpleHashMap.get(6), is("value3"));
    }

    @Test
    public void whenAddItemThenDelete() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.insert(2, "value1");
        simpleHashMap.insert(6, "value3");
        simpleHashMap.insert(15, "value2");
        simpleHashMap.delete(6);
        assertThat(simpleHashMap.get(6), nullValue());
    }

    @Test
    public void whenAddAndResize() {
        SimpleHashMap<Integer, String> simpleHashMap = new SimpleHashMap<>();
        for (int i = 0; i < 48; i++) {
            simpleHashMap.insert(i, "Value" + i);
        }
        assertThat(simpleHashMap.size(), is(48));
    }

}