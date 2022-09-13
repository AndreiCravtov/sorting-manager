package com.sparta.andrei.structures;

import java.util.ArrayList;

public class BinarySearchTree {
    private BinarySearchTreeNode root;

    public void add(int value) {
        if (root == null) {
            root = new BinarySearchTreeNode(value);
            return;
        }
        BinarySearchTreeNode currentNode = root;
        while (true) {
            BinarySearchTreeNode parentNode = currentNode;
            if (value == currentNode.value) {
                currentNode.count++;
                break;
            } else if (value > currentNode.value) {
                currentNode = currentNode.right;
                if (currentNode == null) {
                    currentNode = new BinarySearchTreeNode(value);
                    parentNode.right = currentNode;
                    break;
                }
            } else {
                currentNode = currentNode.left;
                if (currentNode == null) {
                    currentNode = new BinarySearchTreeNode(value);
                    parentNode.left = currentNode;
                    break;
                }
            }
        }
    }

    public int[] inOrderTraversal() {
        ArrayList<Integer> traversal = new ArrayList<>();
        inOrderTraversal(traversal, root);
        return traversal.stream().mapToInt(i -> i).toArray();
    }

    private void inOrderTraversal(ArrayList<Integer> traversal, BinarySearchTreeNode node) {
        if (node == null) return;
        inOrderTraversal(traversal, node.left);
        for (int i = 0; i < node.count; i++)
            traversal.add(node.value);
        inOrderTraversal(traversal, node.right);
    }
}

class BinarySearchTreeNode {
    int value;
    int count;
    BinarySearchTreeNode left, right;

    BinarySearchTreeNode(int _value) {
        value = _value;
        count = 1;
        left = right = null;
    }
}
