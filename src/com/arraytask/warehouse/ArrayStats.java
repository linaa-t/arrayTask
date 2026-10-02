package com.arraytask.warehouse;

public class ArrayStats {
    private int id;
    private int sum;
    private double average;
    private int max;
    private int min;

    public ArrayStats(int id, int sum, double average, int max, int min) {
        this.id = id;
        this.sum = sum;
        this.average = average;
        this.max = max;
        this.min = min;
    }

    public int getId() { return id; }
    public int getSum() { return sum; }
    public double getAverage() { return average; }
    public int getMax() { return max; }
    public int getMin() { return min; }

    @Override
    public String toString() {
        return "ID=" + id + ", сумма=" + sum + ", среднее=" + average + ", max=" + max + ", min=" + min;
    }
}