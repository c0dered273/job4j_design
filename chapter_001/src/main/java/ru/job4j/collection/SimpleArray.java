package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private final int DEFAULT_CAPACITY = 10;
    private final Object[] EMPTY_CONTAINER = {};
    private int size = 0;
    private int modCount = 0;
    private Object[] container;

    public SimpleArray(int initCapacity) {
        container = new Object[initCapacity];
    }

    public SimpleArray() {
        container = new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    public void add(T model) {
        if (size == container.length) {
            container = grow();
        }
        container[size] = model;
        modCount++;
        size++;
    }

    private Object[] grow() {
        int newCapacity = container.length + container.length / 2;
        if (container.length > 0 || container != EMPTY_CONTAINER) {
            return Arrays.copyOf(container, newCapacity);
        } else {
            return new Object[DEFAULT_CAPACITY];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        private int cursor;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) container[cursor++];
        }
    }
}