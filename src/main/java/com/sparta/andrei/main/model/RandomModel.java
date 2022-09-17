package com.sparta.andrei.main.model;

import java.util.Random;

public class RandomModel {
    private int[] array;
    private final Random random;

    public RandomModel() {
        array = new int[0];
        random = new Random();
    }

    public void setArray(int length, int lower, int upper) {
        assert length >= 0;
        assert lower <= upper;

        array = new int[length];
        for (int i=0; i<length; i++)
            array[i] = random.nextInt(lower, upper + 1);
    }

    public int[] getArray() {
        return array;
    }
}
