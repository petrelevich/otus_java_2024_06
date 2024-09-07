package ru.otus.l12.homework.money;

public enum MoneyDenomination {
    FIVE_THOUSAND(5_000),
    ONE_THOUSAND(1_000),
    FIVE_HUNDRED(500),
    ONE_HUNDRED(100),
    FIFTY(50);

    private final int numRepresentation;

    MoneyDenomination(int numRepresentation) {
        this.numRepresentation = numRepresentation;
    }

    public int numRepresentation() {
        return numRepresentation;
    }
}