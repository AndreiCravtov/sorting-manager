package com.sparta.andrei.sorters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MergeSortTest {
    public static Sorter sorter = new MergeSort();

    @Test
    @DisplayName("Test sort with only positive numbers")
    void testOnlyPositive() {
        Assertions.assertArrayEquals(
                sorter.sortArray(new int[]{5, 5, 7, 8, 2, 4, 1}),
                new int[]{1, 2, 4, 5, 5, 7, 8}
        );
    }

    @Test
    @DisplayName("Test sort with only negative numbers")
    void testOnlyNegative() {
        Assertions.assertArrayEquals(
                sorter.sortArray(new int[]{-1, -3, -5, -7, -9, -5}),
                new int[]{-9, -7, -5, -5, -3, -1}
        );
    }

    @Test
    @DisplayName("Test sort with positive and negative numbers")
    void testPositiveNegative() {
        Assertions.assertArrayEquals(
                sorter.sortArray(new int[]{0, 5, -6, -5, -4, 5, 7, 8, 2, 4, 1}),
                new int[]{-6, -5, -4, 0, 1, 2, 4, 5, 5, 7, 8}
        );
    }

    @Test
    @DisplayName("Test sort with the same number")
    void testSameNumber() {
        Assertions.assertArrayEquals(
                sorter.sortArray(new int[]{1, 1, 1, 1}),
                new int[]{1, 1, 1, 1}
        );
    }

    @Test
    @DisplayName("Test sort with an empty list")
    void testEmptyList() {
        Assertions.assertArrayEquals(
                sorter.sortArray(new int[]{}),
                new int[]{}
        );
    }

    @Test
    @DisplayName("Test that the sort doesn't change the original array")
    void testNonModification() {
        int[] array = {0, 5, -6, -5, -4, 5, 7, 8, 2, 4, 1};
        sorter.sortArray(array);
        Assertions.assertArrayEquals(
                array,
                new int[] {0, 5, -6, -5, -4, 5, 7, 8, 2, 4, 1}
        );
    }
}
