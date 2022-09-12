package com.sparta.andrei.main;

import com.sparta.andrei.sorters.Sorter;
import com.sparta.andrei.sorters.TreeSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Sorter sorter = new TreeSort();
        int[] unsorted = {0, 5, -6, -5, -4, 5, 7, 8, 2, 4, 1};
        int[] sorted = sorter.sortArray(unsorted);
        System.out.println(Arrays.toString(sorted));
    }
}