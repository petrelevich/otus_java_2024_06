package ru.otus.l12.homework.money;

import java.util.Objects;

public abstract class Money {
    private final MoneyDenomination denomination;

    Money(MoneyDenomination denomination) {
        this.denomination = denomination;
    }

    public MoneyDenomination getDenomination() {
        return this.denomination;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return denomination == money.denomination;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(denomination);
    }
}
