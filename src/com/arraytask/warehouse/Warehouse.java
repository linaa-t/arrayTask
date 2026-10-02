package com.arraytask.warehouse;

import com.arraytask.entity.IntArrayEntity;
import com.arraytask.repository.ArrayChangeListener;
import com.arraytask.repository.ArrayRepository;
import com.arraytask.service.ArrayMathService;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class Warehouse implements ArrayChangeListener {

    private static Warehouse instance;
    private Map<Integer, ArrayStats> statsMap = new HashMap<>();
    private ArrayMathService mathService = new ArrayMathService();

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void registerToRepository(ArrayRepository repository) {
        repository.addListener(this);
    }

    @Override
    public void onArrayAdded(IntArrayEntity entity) {
        recalculate(entity);
    }

    @Override
    public void onArrayRemoved(IntArrayEntity entity) {
        statsMap.remove(entity.getId());
    }

    @Override
    public void onArrayChanged(IntArrayEntity entity) {
        recalculate(entity);
    }

    private void recalculate(IntArrayEntity entity) {
        int[] numbers = entity.getNumbers();
        int id = entity.getId();

        OptionalInt sumOpt = mathService.calculateSum(numbers);
        OptionalDouble avgOpt = mathService.calculateAverage(numbers);
        OptionalInt maxOpt = mathService.findMax(numbers);
        OptionalInt minOpt = mathService.findMin(numbers);

        int sum = sumOpt.isPresent() ? sumOpt.getAsInt() : 0;
        double average = avgOpt.isPresent() ? avgOpt.getAsDouble() : 0.0;
        int max = maxOpt.isPresent() ? maxOpt.getAsInt() : 0;
        int min = minOpt.isPresent() ? minOpt.getAsInt() : 0;

        ArrayStats stats = new ArrayStats(id, sum, average, max, min);
        statsMap.put(id, stats);
    }

    public ArrayStats getStats(int id) {
        return statsMap.get(id);
    }
}
