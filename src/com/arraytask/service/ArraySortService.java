package com.arraytask.service;

public class ArraySortService {

    public int[] sortByBubble(int[] array) {
        int[] sortedArray = array.clone();
        int arrayLength = sortedArray.length;

        for (int i = 0; i < arrayLength - 1; i++) {
            for (int j = 0; j < arrayLength - 1 - i; j++) {
                if (sortedArray[j] > sortedArray[j + 1]) {
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                }
            }
        }
        return sortedArray;
    }

    public int[] sortBySelection(int[] array) {
        int[] sortedArray = array.clone();
        int arrayLength = sortedArray.length;

        for (int i = 0; i < arrayLength - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arrayLength; j++) {
                if (sortedArray[j] < sortedArray[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = sortedArray[minIndex];
            sortedArray[minIndex] = sortedArray[i];
            sortedArray[i] = temp;
        }
        return sortedArray;
    }
}