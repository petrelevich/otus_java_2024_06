package ru.otus;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings({"java:S3011", "java:S1191"})
public class MyArrayInt implements AutoCloseable {
    private final sun.misc.Unsafe unsafe;
    private final long elementSizeBytes;
    private long size;
    private long arrayBeginIdx;

    public MyArrayInt(int size)
            throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        this.size = size;

        Constructor<sun.misc.Unsafe> unsafeConstructor = sun.misc.Unsafe.class.getDeclaredConstructor();
        unsafeConstructor.setAccessible(true);
        unsafe = unsafeConstructor.newInstance();
        elementSizeBytes = Integer.SIZE / 8;
        arrayBeginIdx = unsafe.allocateMemory(this.size * elementSizeBytes);
    }

    public void setValue(long index, int value) {
        if (index >= size) {
            this.size = Math.max(this.size * 2, index + 1);
            arrayBeginIdx = unsafe.reallocateMemory(arrayBeginIdx, this.size * elementSizeBytes);
        }
        unsafe.putInt(calcIndex(index), value);
    }

    public int getValue(long index) {
        return unsafe.getInt(calcIndex(index));
    }

    @Override
    public void close() {
        unsafe.freeMemory(arrayBeginIdx);
    }

    private long calcIndex(long offset) {
        return arrayBeginIdx + offset * this.elementSizeBytes;
    }

    public long getSize() {
        return size;
    }
}
