package ru.outs.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Car implements Strategy {
    private static final Logger logger = LoggerFactory.getLogger(Car.class);

    @Override
    public void transportation() {
        logger.info("car");
    }
}
