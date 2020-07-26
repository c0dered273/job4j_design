package ru.job4j.exercises;


/**
 * Задача: объединить два массива
 * Даны два отсортированных массива, получить третий, тоже отсортированный
 */
public class MergeArrays {

    public int[] merge(int[] left, int[] right) {
        int[] resultArray = new int[left.length + right.length];
        int leftPtr = 0;
        int rightPtr = 0;
        int resultPtr = 0;
        while (leftPtr < left.length && rightPtr < right.length) {
            if (left[leftPtr] < right[rightPtr]) {
                resultArray[resultPtr++] = left[leftPtr++];
            } else {
                resultArray[resultPtr++] = right[rightPtr++];
            }
        }
        while (leftPtr < left.length) {
            resultArray[resultPtr++] = left[leftPtr++];
        }
        while (rightPtr < right.length) {
            resultArray[resultPtr++] = right[rightPtr++];
        }
        return resultArray;
    }

}
