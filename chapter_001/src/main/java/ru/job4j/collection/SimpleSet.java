package ru.job4j.collection;


import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<E> implements Iterable<E> {
    private final SimpleArray<E> container = new SimpleArray<>();

    public void add(E e) {
        if (!contains(e)) {
            container.add(e);
        }
    }

    public boolean contains(E e) {
        for (E value : container) {
            if (Objects.equals(value, e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}
