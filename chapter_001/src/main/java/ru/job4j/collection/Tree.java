package ru.job4j.collection;

import java.util.*;
import java.util.function.Predicate;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> findParent = findBy(parent);
        Optional<Node<E>> findChild = findBy(child);
        if (findParent.isPresent() && findChild.isEmpty()) {
            Node<E> newNode = new Node<>(child);
            findParent.get().children.add(newNode);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByCondition(e -> e.value.equals(value));
    }

    public boolean isBinary() {
        return findByCondition(e -> e.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByCondition(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer((this.root));
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}