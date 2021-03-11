package ru.job4j.pool;

import org.junit.Test;
import java.util.concurrent.ForkJoinPool;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ArraySearchTest {
    @Test
    public void whenSearchArrayLessThenTen() {
        String[] arr = {"111", "222", "333"};
        String searchValue = "333";
        ArraySearch<String> arraySearch = new ArraySearch<>(arr, searchValue, 0, arr.length - 1);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int result = forkJoinPool.invoke(arraySearch);
        int expect = 2;
        assertThat(result, is(expect));
    }

    @Test
    public void whenSearchArrayGreaterThenTen() {
        String[] arr = {"111", "222", "333", "444", "555", "666", "777", "888", "999", "1010", "1111"};
        String searchValue = "333";
        ArraySearch<String> arraySearch = new ArraySearch<>(arr, searchValue, 0, arr.length - 1);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int result = forkJoinPool.invoke(arraySearch);
        int expect = 2;
        assertThat(result, is(expect));
    }

    @Test
    public void whenSearchEmptyArray() {
        String[] arr = {};
        String searchValue = "333";
        ArraySearch<String> arraySearch = new ArraySearch<>(arr, searchValue, 0, arr.length - 1);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int result = forkJoinPool.invoke(arraySearch);
        int expect = -1;
        assertThat(result, is(expect));
    }

    @Test
    public void whenSearchNoValue() {
        String[] arr = {"111", "222", "333", "444", "555", "666", "777", "888", "999", "1010", "1111"};
        String searchValue = "31337";
        ArraySearch<String> arraySearch = new ArraySearch<>(arr, searchValue, 0, arr.length - 1);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        int result = forkJoinPool.invoke(arraySearch);
        int expect = -1;
        assertThat(result, is(expect));
    }

}