package com.arraytask.repository;

import com.arraytask.entity.IntArrayEntity;

public interface ArrayChangeListener {
    void onArrayAdded(IntArrayEntity entity);
    void onArrayRemoved(IntArrayEntity entity);
    void onArrayChanged(IntArrayEntity entity);
}
