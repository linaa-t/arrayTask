package com.arraytask.specification;

import com.arraytask.entity.IntArrayEntity;

public interface ArraySpecification {
    boolean isSatisfiedBy(IntArrayEntity entity);
}