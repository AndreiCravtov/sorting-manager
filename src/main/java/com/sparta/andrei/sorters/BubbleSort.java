package com.sparta.andrei.sorters;

import java.util.Arrays;

public class BubbleSort implements Sorter {
    @Override
    public int[] sortArray(int[] arrayToSort) {
        int[] returnArr = Arrays.copyOf(arrayToSort, arrayToSort.length);
        boolean swapped;
        for (int i=returnArr.length-1; i>0; i--) {
            swapped = false;
            for (int j=0; j<i; j++)
                if (returnArr[j] > returnArr[j+1]) {
                    returnArr[j] = returnArr[j] ^ returnArr[j+1] ^ (returnArr[j+1] = returnArr[j]);
                    swapped = true;
                }
            if (!swapped) break;
        }
        return returnArr;
    }
}
