package ru.otus.l12.homework.storage;

import ru.otus.l12.homework.money.Money;
import ru.otus.l12.homework.money.MoneyDenomination;

import java.util.List;
import java.util.Map;

public interface MoneyStorage<T extends Money> {
    List<T> getMoney(Map<MoneyDenomination, Integer> requiredMoney);

    void putMoney(Map<MoneyDenomination, Integer> money);
}
