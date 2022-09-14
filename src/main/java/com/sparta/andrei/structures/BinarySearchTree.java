package com.sparta.andrei.structures;

import java.util.ArrayList;

public class BinarySearchTree {
    class Node {
        int value;
        int count;
        Node left, right;

        Node(int value) {
            this.value = value;
            count = 1;
            left = right = null;
        }
    }
    private Node root;

    public void add(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        Node currentNode = root;
        while (true) {
            Node parentNode = currentNode;
            if (value == currentNode.value) {
                currentNode.count++;
                break;
            } else if (value > currentNode.value) {
                currentNode = currentNode.right;
                if (currentNode == null) {
                    currentNode = new Node(value);
                    parentNode.right = currentNode;
                    break;
                }
            } else {
                currentNode = currentNode.left;
                if (currentNode == null) {
                    currentNode = new Node(value);
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

    private void inOrderTraversal(ArrayList<Integer> traversal, Node node) {
        if (node == null) return;
        inOrderTraversal(traversal, node.left);
        for (int i = 0; i < node.count; i++)
            traversal.add(node.value);
        inOrderTraversal(traversal, node.right);
    }
}
