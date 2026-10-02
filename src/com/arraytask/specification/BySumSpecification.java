package com.arraytask.specification;

import com.arraytask.entity.IntArrayEntity;
import com.arraytask.service.ArrayMathService;
import java.util.OptionalInt;

public class BySumSpecification implements ArraySpecification {
    private int threshold;
    private ComparisonType type;
    private ArrayMathService mathService = new ArrayMathService();

    public BySumSpecification(int threshold, ComparisonType type) {
        this.threshold = threshold;
        this.type = type;
    }

    @Override
    public boolean isSatisfiedBy(IntArrayEntity entity) {
        OptionalInt sumOpt = mathService.calculateSum(entity.getNumbers());
        if (!sumOpt.isPresent()) {
            return false;
        }
        int sum = sumOpt.getAsInt();
        return compare(sum);
    }

    private boolean compare(int value) {
        if (type == ComparisonType.GREATER) {
            return value > threshold;
        } else if (type == ComparisonType.LESS) {
            return value < threshold;
        } else {
            return value == threshold;
        }
    }
}