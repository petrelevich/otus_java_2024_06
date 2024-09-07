package ru.otus.l12.homework;

import ru.otus.l12.homework.money.CashMoney;
import ru.otus.l12.homework.money.Money;
import ru.otus.l12.homework.money.MoneyDenomination;
import ru.otus.l12.homework.storage.CashMoneyStorage;
import ru.otus.l12.homework.storage.MoneyStorage;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CashAtmImplementation implements Atm<CashMoney>{
    private final BalanceManager balanceManager;
    private final MoneyStorage<CashMoney> moneyStorage;

    public CashAtmImplementation() {
        this.balanceManager = new BalanceManager();
        this.moneyStorage = new CashMoneyStorage();
    }

    @Override
    public void insertMoney(List<CashMoney> cash) {
        var groupedDenomination = cash.stream()
            .map(Money::getDenomination)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(MoneyDenomination::numRepresentation)));

        balanceManager.registerReceivedMoney(groupedDenomination);
        moneyStorage.putMoney(groupedDenomination);
    }

    @Override
    public List<CashMoney> getMoney(long requestedSum) {
        var receivedMoney = balanceManager.tryToReceiveMoney(requestedSum);
        return moneyStorage.getMoney(receivedMoney);
    }

    @Override
    public long remainingSum() {
        return balanceManager.remainingAccessibleSum();
    }
}
