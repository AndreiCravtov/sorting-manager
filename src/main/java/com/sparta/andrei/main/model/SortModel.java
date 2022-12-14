package com.sparta.andrei.main.model;

import com.sparta.andrei.errors.SorterIsNull;
import com.sparta.andrei.sorters.Sorter;
import com.sparta.andrei.sorters.SorterFactory;

public class SortModel {
    private int[] unsorted;
    private int[] sorted;
    private final SorterFactory sorterFactory;
    private Sorter sorter;

    public SortModel() {
        sorterFactory = new SorterFactory();
        unsorted = new int[0];
        sorted = new int[0];
    }

    public SortModel(SorterFactory.SorterType type) {
        sorterFactory = new SorterFactory();
        unsorted = new int[0];
        sorted = new int[0];
        sorter = sorterFactory.getNewSorter(type);
    }

    public int[] getSorted() {
        return sorted;
    }

    public int[] getUnsorted() {
        return unsorted;
    }

    public void setUnsorted(int[] unsorted) {
        this.unsorted = unsorted;
    }

    public void setSorter(SorterFactory.SorterType type) {
        sorter = sorterFactory.getNewSorter(type);
    }

    public long sort() throws SorterIsNull {
        if (sorter == null) throw new SorterIsNull();

        long startTime = System.nanoTime();
        sorted = sorter.sortArray(unsorted);
        long endTime =System.nanoTime();

        return endTime - startTime;
    }
}
