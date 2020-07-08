package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class EvenIterator implements Iterator<Integer> {
    private int[] data;
    private int pointer;

    public EvenIterator(int[] data) {
        this.data = data;
        this.pointer = getNextEvenIndex();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void forEachRemaining(Consumer<? super Integer> action) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
        return pointer != -1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = data[pointer++];
        pointer = getNextEvenIndex();
        return result;
    }

    private int getNextEvenIndex() {
        int result = -1;
        for (int i = pointer; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        return result;
    }
}
