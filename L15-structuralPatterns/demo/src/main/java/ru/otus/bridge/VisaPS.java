package ru.otus.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VisaPS implements PaymentSystem {
    private static final Logger logger = LoggerFactory.getLogger(VisaPS.class);

    @Override
    public void printName() {
        logger.info("VisaPS");
    }
}
