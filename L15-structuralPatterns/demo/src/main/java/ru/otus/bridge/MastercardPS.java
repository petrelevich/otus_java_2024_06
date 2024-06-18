package ru.otus.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MastercardPS implements PaymentSystem {
    private static final Logger logger = LoggerFactory.getLogger(MastercardPS.class);

    @Override
    public void printName() {
        logger.info("MastercardPS");
    }
}
