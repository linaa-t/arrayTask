package com.arraytask.comparator;

import com.arraytask.entity.IntArrayEntity;
import java.util.Comparator;

public class BySizeComparator implements Comparator<IntArrayEntity> {
    @Override
    public int compare(IntArrayEntity e1, IntArrayEntity e2) {
        return Integer.compare(e1.getSize(), e2.getSize());
    }
}