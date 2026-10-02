package com.arraytask.specification;

import com.arraytask.entity.IntArrayEntity;

public class ByIdSpecification implements ArraySpecification {
    private int targetId;

    public ByIdSpecification(int targetId) {
        this.targetId = targetId;
    }

    @Override
    public boolean isSatisfiedBy(IntArrayEntity entity) {
        return entity.getId() == targetId;
    }
}