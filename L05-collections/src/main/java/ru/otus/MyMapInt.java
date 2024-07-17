package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyMapInt {
    private static final Logger logger = LoggerFactory.getLogger(MyMapInt.class);

    private final int size;
    private static final int STEP_LIMIT = 5;
    private final String[] entriesKey;
    private final int[] entriesValue;
    private final boolean[] entriesOccupied;

    public MyMapInt(int size) {
        this.size = size * 8 - 1;
        entriesKey = new String[this.size];
        entriesValue = new int[this.size];
        entriesOccupied = new boolean[this.size];
    }

    public void put(String key, int value) {
        var index = calcIndex(key);

        this.entriesKey[index] = key;
        this.entriesValue[index] = value;
        this.entriesOccupied[index] = true;
    }

    public int get(String key) {
        var step = 0;
        int index;
        do {
            index = getBaseIndex(key, step++);
        } while (index >= 0 && !key.equals(entriesKey[index]));
        if (index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return entriesValue[index];
    }

    private int getBaseIndex(String key, int step) {
        if (step >= STEP_LIMIT) {
            logger.info("key:{}, step:{}", key, step);
            return -1;
        }
        var hash = key.hashCode();
        return (((hash + step * hash) & 0x7fffffff) % size);
    }

    private int calcIndex(String key) {
        var step = 0;
        int index;
        do {
            index = getBaseIndex(key, step++);
        } while (index >= 0 && entriesOccupied[index]);
        return index;
    }
}
