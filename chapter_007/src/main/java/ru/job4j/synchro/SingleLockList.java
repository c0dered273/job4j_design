package ru.job4j.synchro;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.collection.SimpleArray;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final SimpleArray<T> simpleArray = new SimpleArray<>();

    public synchronized void add(T value) {
        simpleArray.add(value);
    }

    public synchronized T get(int index) {
        return simpleArray.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.simpleArray).iterator();
    }

    private SimpleArray<T> copy(SimpleArray<T> simpleArray) {
        var rsl = new SimpleArray<T>();
        simpleArray.forEach(rsl::add);
        return rsl;
    }
}
