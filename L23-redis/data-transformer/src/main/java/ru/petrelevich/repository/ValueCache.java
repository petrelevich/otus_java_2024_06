package ru.petrelevich.repository;

import java.util.Optional;

public interface ValueCache {

    Optional<Long> get(long val);

    void put(long val);
}
