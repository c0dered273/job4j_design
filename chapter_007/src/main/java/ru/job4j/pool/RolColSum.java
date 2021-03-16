package ru.job4j.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Async calculations practice with CompletableFuture.
 */
public class RolColSum {

    /**
     * Auxiliary class with calculations result.
     */
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }

    private RolColSum() {
    }

    /**
     * Linear calculations.
     *
     * @param matrix matrix
     * @return Sums[]
     */
    public static Sums[] sum(int[][] matrix) {
        int n = matrix.length;
        Sums[] rslSums = new Sums[n];
        for (int i = 0; i < n; i++) {
            rslSums[i] = getRowColSum(matrix, i);
        }
        return rslSums;
    }

    /**
     * Async calculations.
     *
     * @param matrix matrix
     * @return Sums[]
     */
    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        Sums[] rslSums = new Sums[n];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int finalI = i;
            futures.put(i, CompletableFuture.supplyAsync(
                    () -> getRowColSum(matrix, finalI)
            ));
        }
        for (Map.Entry<Integer, CompletableFuture<Sums>> entry : futures.entrySet()) {
            rslSums[entry.getKey()] = entry.getValue().get();
        }
        return rslSums;
    }

    private static Sums getRowColSum(int[][] matrix, int index) {
        int n = matrix.length;
        Sums rsl = new Sums();
        int rowSum = 0;
        int colSum = 0;
        for (int counter = 0; counter < n; counter++) {
            rowSum += matrix[index][counter];
            colSum += matrix[counter][index];
        }
        rsl.setRowSum(rowSum);
        rsl.setColSum(colSum);
        return rsl;
    }
}
