package ru.otus.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreditCard extends Card {
    private static final Logger logger = LoggerFactory.getLogger(CreditCard.class);

    public CreditCard(PaymentSystem paymentSystem) {
        super(paymentSystem);
    }

    @Override
    protected void cardType() {
        logger.info("credit card");
    }
}
