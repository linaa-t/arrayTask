package com.arraytask.entity;

public class IntArrayEntity {
    private int id;
    private int[] numbers;

    public IntArrayEntity(int id, int[] numbers) {
        this.id = id;
        this.numbers = numbers;
    }

    public IntArrayEntity(int[] resultArray) {
    }

    public int getId() {
        return id;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setElement(int index, int value) {
        numbers[index] = value;
    }

    public int getFirstElement() {
        return numbers[0];
    }

    public int getSize() {
        return numbers.length;
    }
}