package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final Object[] elements;
    private int size;

    public SimpleArray(int initSize) {
        elements = new Object[initSize];
        size = 0;
    }

    public boolean add(T model) {
        boolean result = false;
        if (size < elements.length) {
            elements[size++] = model;
            result = true;
        }
        return result;
    }

    public T set(int index, T model) {
        Objects.checkIndex(index, size);
        T oldValue = getElement(index);
        elements[index] = model;
        return oldValue;
    }

    public T remove(int index) {
        Objects.checkIndex(index, size);
        T oldValue = getElement(index);
        int newSize = size - 1;
        if (index < newSize) {
            System.arraycopy(elements, index + 1, elements, index, newSize - index);
        }
        elements[size = newSize] = null;
        return oldValue;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        return getElement(index);
    }

    @SuppressWarnings("unchecked")
    private T getElement(int index) {
        return (T) elements[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return getElement(cursor++);
            }
        };
    }
}
