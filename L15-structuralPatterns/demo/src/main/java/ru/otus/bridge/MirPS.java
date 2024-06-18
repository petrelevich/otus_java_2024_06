package ru.otus.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MirPS implements PaymentSystem {
    private static final Logger logger = LoggerFactory.getLogger(MirPS.class);

    @Override
    public void printName() {
        logger.info("Mir");
    }
}
