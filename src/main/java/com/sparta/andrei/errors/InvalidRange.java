package com.sparta.andrei.errors;

public class InvalidRange extends Exception {
    public InvalidRange() {
        super("This range is invalid. The lower bound is higher than the upper bound.");
    }
}
