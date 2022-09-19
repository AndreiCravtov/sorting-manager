package com.sparta.andrei.errors;

public class SorterIsNull extends Exception{
    public SorterIsNull() {
        super("The sorter is null. Please select a sorter first.");
    }
}
