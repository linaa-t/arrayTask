package com.arraytask.service;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public class ArrayMathService {

    public OptionalInt findMin(int[] array) {
        boolean isEmpty = array == null || array.length == 0;
        if (isEmpty) {
            return OptionalInt.empty();
        }

        int minValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
            }
        }
        return OptionalInt.of(minValue);
    }

    public OptionalInt findMax(int[] array) {
        boolean isEmpty = array == null || array.length == 0;
        if (isEmpty) {
            return OptionalInt.empty();
        }

        int maxValue = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
            }
        }
        return OptionalInt.of(maxValue);
    }

    public OptionalInt calculateSum(int[] array) {
        boolean isEmpty = array == null || array.length == 0;
        if (isEmpty) {
            return OptionalInt.empty();
        }

        int sumValue = 0;
        for (int number : array) {
            sumValue += number;
        }
        return OptionalInt.of(sumValue);
    }

    public OptionalDouble calculateAverage(int[] array) {
        boolean isEmpty = array == null || array.length == 0;
        if (isEmpty) {
            return OptionalDouble.empty();
        }

        OptionalInt sumOptional = calculateSum(array);
        int sumValue = sumOptional.getAsInt();
        double averageValue = (double) sumValue / array.length;

        return OptionalDouble.of(averageValue);
    }
}