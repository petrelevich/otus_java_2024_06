package ru.outs.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Taxi implements Strategy {
    private static final Logger logger = LoggerFactory.getLogger(Taxi.class);

    @Override
    public void transportation() {
        logger.info("taxi");
    }
}
