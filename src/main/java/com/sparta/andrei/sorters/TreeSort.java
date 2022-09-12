package com.sparta.andrei.sorters;

import com.sparta.andrei.structures.SortedBinaryTree;

public class TreeSort implements Sorter {
    @Override
    public int[] sortArray(int[] arrayToSort) {
        SortedBinaryTree tree = new SortedBinaryTree();
        for (int number: arrayToSort) {
            tree.add(number);
        }
        return tree.inOrderTraversal();
    }
}
