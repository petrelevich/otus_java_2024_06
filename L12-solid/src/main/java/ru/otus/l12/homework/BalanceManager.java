package ru.otus.l12.homework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.l12.homework.money.MoneyDenomination;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class BalanceManager {
    private final Logger logger = LoggerFactory.getLogger(BalanceManager.class);

    private final Map<MoneyDenomination, Integer> denominationByCountInfo;
    private int balance;

    public BalanceManager() {
        this.denominationByCountInfo = new HashMap<>();
    }

    public void registerReceivedMoney(Map<MoneyDenomination, Integer> money) {
        var insertedSum = money.values().stream().reduce(Integer::sum).get();
        balance += insertedSum;

        money.forEach((denomination,  sum) -> denominationByCountInfo.merge(denomination, sum, Integer::sum));
    }

    public Map<MoneyDenomination, Integer> tryToReceiveMoney(long amount) {
        if (amount <= 0) {
            logger.warn("Requested money can't be less than 0");
        }

        if (amount > remainingAccessibleSum()) {
            logger.warn("There is no enough money to be received");
            return Map.of();
        }

        var moneyForRequestedAmount = computeMoneyForRequestedAmount(amount);

        if (moneyForRequestedAmount.isEmpty()) {
            logger.warn("There is no needed money for the required sum: {}", amount);
            return Map.of();
        }

        removeMoneyFromCurrentBalance(moneyForRequestedAmount);

        return moneyForRequestedAmount;
    }

    public int remainingAccessibleSum() {
        return balance;
    }

    private Map<MoneyDenomination, Integer> computeMoneyForRequestedAmount(long amount) {
        long remainingSum = amount;
        var takenMoney = new HashMap<MoneyDenomination, Integer>();
        var denominations = denominationByCountInfo.keySet().stream()
            .sorted(Comparator.comparing(MoneyDenomination::numRepresentation).reversed())
            .toList();

        for (var denomination : denominations) {
            if (remainingSum < denomination.numRepresentation()) {
                continue;
            }
            var sumContainDenomination = (int) remainingSum / denomination.numRepresentation();
            var canTake = Math.min(
                sumContainDenomination,
                denominationByCountInfo.get(denomination)
            );

            remainingSum -= (denomination.numRepresentation() * canTake);
            takenMoney.put(denomination, canTake);

            if (remainingSum == 0) break;
        }

        if (remainingSum != 0) {
            return Map.of();
        }

        return takenMoney;
    }

    private void removeMoneyFromCurrentBalance(Map<MoneyDenomination, Integer> moneyToRemove) {
        moneyToRemove.entrySet().stream()
            .forEach(entry -> denominationByCountInfo.merge(
                entry.getKey(),
                entry.getValue(),
                (curCount, remCount) -> curCount - remCount)
            );
    }
}
