package com.arraytask.util;

import com.arraytask.entity.IntArrayEntity;
import com.arraytask.exception.InvalidDataException;
import java.util.ArrayList;
import java.util.List;

public class DataParser {

    private static final String NUMBER_REGEX = "^-?\\d+(\\.\\d+)?$";
    private static final String SEPARATOR_REGEX = "[,;\\s\\-–]+";

    public boolean isValidLine(String line) {
        if (line.trim().isEmpty()) {
            return true;
        }

        String[] parts = line.split(SEPARATOR_REGEX);
        for (String part : parts) {
            boolean isPartEmpty = part.isEmpty();
            if (!isPartEmpty && !part.matches(NUMBER_REGEX)) {
                return false;
            }
        }
        return true;
    }

    public IntArrayEntity createIntArray(String line) throws InvalidDataException {
        boolean isValid = isValidLine(line);
        if (!isValid) {
            throw new InvalidDataException("Строка содержит некорректные данные: " + line);
        }

        String[] stringParts = line.split(SEPARATOR_REGEX);
        List<Integer> validNumbers = new ArrayList<>();

        for (String part : stringParts) {
            boolean isPartEmpty = part.isEmpty();
            if (!isPartEmpty) {
                int number = Integer.parseInt(part);
                validNumbers.add(number);
            }
        }

        int[] resultArray = new int[validNumbers.size()];
        for (int i = 0; i < validNumbers.size(); i++) {
            resultArray[i] = validNumbers.get(i);
        }

        return new IntArrayEntity(resultArray);
    }
}