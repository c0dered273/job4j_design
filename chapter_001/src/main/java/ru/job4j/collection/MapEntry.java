package ru.job4j.collection;

public interface MapEntry<K, V> {
    K getKey();

    V getValue();

    V setValue(V newValue);
}
