package ru.job4j.collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<MapEntry<K, V>> {
    static final int DEFAULT_CAPACITY = 16;
    static final double DEFAULT_THRESHOLD = 0.75;

    private Node<K, V>[] table;
    private int capacity;
    private int size;
    private int threshold;
    private int modCount;

    @SuppressWarnings("unchecked")
    public SimpleHashMap() {
        table = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
        threshold = (int) (DEFAULT_CAPACITY * DEFAULT_THRESHOLD);
    }

    public boolean insert(K key, V value) {
        int index = indexOf(key);
        if (table[index] != null) {
            return false;
        }
        table[index] = new Node<>(key, value);
        if (++size > threshold) {
            resize();
        }
        ++modCount;
        return true;
    }

    public V get(K key) {
        V result = null;
        int index = indexOf(key);
        if (table[index] != null) {
            if (Objects.equals(key, table[index].getKey())) {
                result = table[index].getValue();
            }
        }
        return result;
    }

    public boolean delete(K key) {
        int index = indexOf(key);
        if (table[index] != null) {
            if (Objects.equals(key, table[index].getKey())) {
                table[index] = null;
                --size;
                ++modCount;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Node<K, V>[] oldTable = table;
        int oldCap = capacity;
        int newCap = capacity << 1;
        table = (Node<K, V>[]) new Node[newCap];
        Node<K, V> e;
        for (int i = 0; i < oldCap; i++) {
            if ((e = oldTable[i]) != null) {
                oldTable[i] = null;
                int newIndex = indexOf(e.getKey());
                table[newIndex] = e;
            }
        }
        capacity = newCap;
        threshold = threshold << 1;
        ++modCount;
    }

    private int hash(Object key) {
        int hash;
        return (key == null) ? 0 : (hash = key.hashCode()) ^ (hash >>> 16);
    }

    private int indexOf(K key) {
        return (table.length - 1) & hash(key);
    }

    @Override
    public Iterator<MapEntry<K, V>> iterator() {
        return new Iterator<>() {
            private int cursor;
            private int elementsRemain = size;
            private final int expectedModCount = modCount;


            @Override
            public boolean hasNext() {
                return elementsRemain > 0;
            }

            @Override
            public MapEntry<K, V> next() {
                MapEntry<K, V> result = null;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                for (int i = cursor; i < table.length; i++) {
                    if (table[i] != null) {
                        cursor = i + 1;
                        --elementsRemain;
                        result = table[i];
                        break;
                    }
                }
                return result;
            }
        };
    }

    static class Node<K, V> implements MapEntry<K, V> {
        final K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
    }
}
