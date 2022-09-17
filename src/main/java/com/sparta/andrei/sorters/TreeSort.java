package com.sparta.andrei.sorters;

import com.sparta.andrei.structures.BinarySearchTree;

public class TreeSort implements Sorter {
    TreeSort(){}
    @Override
    public int[] sortArray(int[] arrayToSort) {
        BinarySearchTree tree = new BinarySearchTree();
        for (int number: arrayToSort)
            tree.add(number);
        return tree.inOrderTraversal();
    }
}
