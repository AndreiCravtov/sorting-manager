package com.sparta.andrei.sorters;

import java.util.Arrays;

public class MergeSort implements Sorter {
    @Override
    public int[] sortArray(int[] arrayToSort) {
        int[] returnVal = Arrays.copyOf(arrayToSort, arrayToSort.length);
        mergeSort(returnVal);
        return returnVal;
    }

    private void mergeSort(int[] arrayToSort) {
        if (arrayToSort.length <= 1) return;

        int mid = arrayToSort.length / 2;
        int[] left = Arrays.copyOfRange(arrayToSort, 0, mid);
        int[] right = Arrays.copyOfRange(arrayToSort, mid, arrayToSort.length);

        mergeSort(left);
        mergeSort(right);

        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length)
            if (left[i] <= right[j])
                arrayToSort[k++] = left[i++];
            else
                arrayToSort[k++] = right[j++];
        while (i < left.length)
            arrayToSort[k++] = left[i++];
        while (j < right.length)
            arrayToSort[k++] = right[j++];
    }
}
