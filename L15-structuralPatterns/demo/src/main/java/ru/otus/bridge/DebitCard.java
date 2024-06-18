package ru.otus.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DebitCard extends Card {
    private static final Logger logger = LoggerFactory.getLogger(DebitCard.class);

    public DebitCard(PaymentSystem paymentSystem) {
        super(paymentSystem);
    }

    @Override
    protected void cardType() {
        logger.info("debit card");
    }
}
