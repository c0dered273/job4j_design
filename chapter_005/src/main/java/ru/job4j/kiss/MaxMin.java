package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        BiFunction<T, T, Boolean> condition = (o1, o2) -> comparator.compare(o1, o2) < 0;
        return compare(value, condition);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        BiFunction<T, T, Boolean> condition = (o1, o2) -> comparator.compare(o1, o2) > 0;
        return compare(value, condition);
    }

    private <T> T compare(List<T> value, BiFunction<T, T, Boolean> condition) {
        T retVal = value.get(0);
        for (T item : value) {
            if (condition.apply(retVal, item)) {
                retVal = item;
            }
        }
        return retVal;
    }
}