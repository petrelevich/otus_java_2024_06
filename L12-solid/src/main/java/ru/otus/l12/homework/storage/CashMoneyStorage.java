package ru.otus.l12.homework.storage;

import ru.otus.l12.homework.money.CashMoney;
import ru.otus.l12.homework.money.CashMoneyFactory;
import ru.otus.l12.homework.money.Money;
import ru.otus.l12.homework.money.MoneyDenomination;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashMoneyStorage implements MoneyStorage<CashMoney> {
    private final Map<Money, Integer> storedMoney;
    private final CashMoneyFactory moneyFactory;

    public CashMoneyStorage() {
        this.storedMoney = new HashMap<>();
        this.moneyFactory = new CashMoneyFactory();
    }

    @Override
    public List<CashMoney> getMoney(Map<MoneyDenomination, Integer> requiredMoney) {
        List<CashMoney> moneyList = new ArrayList<>();
        for (var entry : requiredMoney.entrySet()) {
            var amount = entry.getValue();
            var money = moneyFactory.create(entry.getKey());

            storedMoney.merge(money, amount, (oldMoney, newMoney) -> oldMoney - newMoney);
            for (int i = 0; i < amount; i++) {
                moneyList.add(money);
            }
        }
        return moneyList;
    }

    @Override
    public void putMoney(Map<MoneyDenomination, Integer> money) {
        money.entrySet().forEach(
            entry -> storedMoney.merge(
                moneyFactory.create(entry.getKey()),
                entry.getValue(),
                Integer::sum
            )
        );
    }
}
