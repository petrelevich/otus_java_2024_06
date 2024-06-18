package ru.outs.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bus implements Strategy {
    private static final Logger logger = LoggerFactory.getLogger(Bus.class);

    @Override
    public void transportation() {
        logger.info("bus");
    }
}
