package com.arraytask.comparator;

import com.arraytask.entity.IntArrayEntity;
import java.util.Comparator;

public class ByIdComparator implements Comparator<IntArrayEntity> {
    @Override
    public int compare(IntArrayEntity e1, IntArrayEntity e2) {
        return Integer.compare(e1.getId(), e2.getId());
    }
}