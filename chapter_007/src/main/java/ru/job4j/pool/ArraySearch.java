package ru.job4j.pool;

import java.util.concurrent.RecursiveTask;

/**
 * Asynchronous search value in array and returns its index.
 *
 * @param <T> Result type
 */
public class ArraySearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T searchValue;
    private final int from;
    private final int to;

    /**
     * Constructor.
     *
     * @param array Search data
     * @param searchValue Value to search
     * @param from Start index of array
     * @param to End index of array
     */
    public ArraySearch(T[] array, T searchValue, int from, int to) {
        this.array = array;
        this.searchValue = searchValue;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (array == null) {
            return -1;
        }
        if (array.length > 10) {
            return searchRecursive();
        } else {
            return searchLinear();
        }
    }

    private Integer searchLinear() {
        int rsl = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(searchValue)) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }

    private Integer searchRecursive() {
        int rsl;
        if (from > to) {
            rsl = -1;
        } else if (array[from].equals(searchValue)) {
            rsl = from;
        } else if (array[to].equals(searchValue)) {
            rsl = to;
        } else {
            ArraySearch<T> newSearch = new ArraySearch<>(array, searchValue, from + 1, to - 1);
            newSearch.fork();
            rsl = newSearch.join();
        }
        return rsl;
    }
}
