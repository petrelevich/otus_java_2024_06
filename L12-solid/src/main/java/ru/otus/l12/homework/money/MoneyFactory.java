package ru.otus.l12.homework.money;

import java.util.HashSet;
import java.util.Set;

abstract class MoneyFactory<T extends Money> {
    private final Set<T> cache;

    MoneyFactory() {
        this.cache = new HashSet<>();
    }

    public T create(MoneyDenomination denomination) {
        return cache.stream()
            .filter(el -> el.getDenomination() == denomination)
            .findFirst()
            .orElseGet(() -> {
                var newInstance = createInstance(denomination);
                cache.add(newInstance);
                return newInstance;
            });
    }

    abstract T createInstance(MoneyDenomination denomination);
}
