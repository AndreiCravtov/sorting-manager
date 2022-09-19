package com.sparta.andrei.errors;

public class LengthIsNegative extends Exception {
    public LengthIsNegative() {
        super("The length cannot be negative.");
    }
}
