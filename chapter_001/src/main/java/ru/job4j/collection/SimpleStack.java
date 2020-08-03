package ru.job4j.collection;

public class SimpleStack<T> {
    private final ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        return linked.deleteLast();
    }

    public void push(T value) {
        linked.add(value);
    }

    public boolean empty() {
        return linked.size() == 0;
    }
}