package ru.otus.cachehw;

public class MyCache<K, V> implements HwCache<K, V> {
    // Надо реализовать эти методы

    @Override
    public void put(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V get(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        throw new UnsupportedOperationException();
    }
}
