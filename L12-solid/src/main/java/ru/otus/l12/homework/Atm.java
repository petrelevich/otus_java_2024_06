package ru.otus.l12.homework;

import ru.otus.l12.homework.money.Money;
import java.util.List;
import java.util.Map;

public interface Atm<T extends Money> {
    void insertMoney(List<T> cash);

    List<T> getMoney(long requestedSum);

    long remainingSum();
}
