package ru.otus.composite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Морской пехотинец.
 */
public class Marine implements Unit {
    private static final Logger logger = LoggerFactory.getLogger(Marine.class);

    @Override
    public void move() {
        logger.info("Marine is moving");
    }
}
