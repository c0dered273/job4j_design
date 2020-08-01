package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements Iterable<E> {
    private int size;
    private int modCount;
    private Node<E> first;
    private Node<E> last;

    public void add(E model) {
        Node<E> oldNode = last;
        Node<E> newNode = new Node<>(last, model, null);
        last = newNode;
        if (oldNode == null) {
            first = newNode;
        } else {
            oldNode.next = newNode;
        }
        size++;
        modCount++;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private class Itr implements Iterator<E> {
        private Node<E> currentItem = first;
        private int nextIndex;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> result = currentItem;
            currentItem = currentItem.next;
            nextIndex++;
            return result.item;
        }
    }
}
