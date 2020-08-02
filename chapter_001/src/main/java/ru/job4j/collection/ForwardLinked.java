package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;
    private int size;
    private int modCount;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<T> lastNode = get(size - 1);
        lastNode.next = node;
        size++;
        modCount++;
    }

    public T deleteFirst() {
        checkHeadIsNull();
        T oldValue = head.value;
        head = head.next;
        size--;
        modCount++;
        return oldValue;
    }

    public T deleteLast() {
        checkHeadIsNull();
        Node<T> beforeLast;
        T lastValue;
        if (size == 1) {
            lastValue = head.value;
            head = null;
        } else {
            beforeLast = get(size - 2);
            lastValue = beforeLast.next.value;
            beforeLast.next = null;
        }
        size--;
        modCount--;
        return lastValue;
    }

    public Node<T> get(int index) {
        checkHeadIsNull();
        Node<T> result = head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }

    private void checkHeadIsNull() {
        if (head == null) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}