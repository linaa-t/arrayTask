package com.arraytask.repository;

import com.arraytask.entity.IntArrayEntity;
import com.arraytask.specification.ArraySpecification;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArrayRepository {

    private static ArrayRepository instance;
    private List<IntArrayEntity> entities = new ArrayList<>();
    private List<ArrayChangeListener> listeners = new ArrayList<>();

    private ArrayRepository() {
    }

    public static ArrayRepository getInstance() {
        if (instance == null) {
            instance = new ArrayRepository();
        }
        return instance;
    }

    public void add(IntArrayEntity entity) {
        entities.add(entity);
        notifyAdded(entity);
    }

    public void remove(IntArrayEntity entity) {
        entities.remove(entity);
        notifyRemoved(entity);
    }

    public void updateElement(int id, int index, int value) {
        IntArrayEntity entity = findById(id);
        if (entity != null) {
            entity.setElement(index, value);
            notifyChanged(entity);
        }
    }

    public IntArrayEntity findById(int id) {
        for (IntArrayEntity entity : entities) {
            if (entity.getId() == id) {
                return entity;
            }
        }
        return null;
    }

    public List<IntArrayEntity> findAll() {
        return new ArrayList<>(entities);
    }

    public List<IntArrayEntity> find(ArraySpecification spec) {
        List<IntArrayEntity> result = new ArrayList<>();
        for (IntArrayEntity entity : entities) {
            if (spec.isSatisfiedBy(entity)) {
                result.add(entity);
            }
        }
        return result;
    }

    public List<IntArrayEntity> sort(Comparator<IntArrayEntity> comparator) {
        List<IntArrayEntity> sortedList = new ArrayList<>(entities);
        sortedList.sort(comparator);
        return sortedList;
    }

    public void addListener(ArrayChangeListener listener) {
        listeners.add(listener);
    }

    private void notifyAdded(IntArrayEntity entity) {
        for (ArrayChangeListener listener : listeners) {
            listener.onArrayAdded(entity);
        }
    }

    private void notifyRemoved(IntArrayEntity entity) {
        for (ArrayChangeListener listener : listeners) {
            listener.onArrayRemoved(entity);
        }
    }

    private void notifyChanged(IntArrayEntity entity) {
        for (ArrayChangeListener listener : listeners) {
            listener.onArrayChanged(entity);
        }
    }
}
