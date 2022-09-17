package com.sparta.andrei.sorters;

public class SorterFactory {
    public enum SorterType {
        BUBBLE_SORT("Bubble Sort"),
        MERGE_SORT("Merge Sort"),
        TREE_SORT("Tree Sort");

        private String name;

        SorterType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public Sorter getNewSorter(SorterType choice) {
        Sorter sorter = null;
        switch (choice) {
            case BUBBLE_SORT -> {
                sorter = new BubbleSort();
            }
            case MERGE_SORT -> {
                sorter = new MergeSort();
            }
            case TREE_SORT -> {
                sorter = new TreeSort();
            }
        }
        return sorter;
    }
}
