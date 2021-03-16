package ru.job4j.pool;

import org.junit.Test;
import ru.job4j.pool.RolColSum.*;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RolColSumTest {
    @Test
    public void whenLinearSum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] expect = {
                new Sums(),
                new Sums(),
                new Sums()
        };
        expect[0].setRowSum(6);
        expect[0].setColSum(12);
        expect[1].setRowSum(15);
        expect[1].setColSum(15);
        expect[2].setRowSum(24);
        expect[2].setColSum(18);
        Sums[] result = RolColSum.sum(matrix);
        assertThat(result, is(expect));
    }

    @Test
    public void whenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] expect = {
                new Sums(),
                new Sums(),
                new Sums()
        };
        expect[0].setRowSum(6);
        expect[0].setColSum(12);
        expect[1].setRowSum(15);
        expect[1].setColSum(15);
        expect[2].setRowSum(24);
        expect[2].setColSum(18);
        Sums[] result = RolColSum.asyncSum(matrix);
        assertThat(result, is(expect));
    }
}