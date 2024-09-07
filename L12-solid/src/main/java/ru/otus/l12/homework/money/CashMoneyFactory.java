package ru.otus.l12.homework.money;

public class CashMoneyFactory extends MoneyFactory<CashMoney>{
    @Override
    CashMoney createInstance(MoneyDenomination denomination) {
        return new CashMoney(denomination);
    }
}
