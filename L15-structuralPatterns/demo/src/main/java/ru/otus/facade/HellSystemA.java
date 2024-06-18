package ru.otus.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HellSystemA {
    private static final Logger logger = LoggerFactory.getLogger(HellSystemA.class);

    public void actionA2() {
        logger.info("action A");
    }

    public void actionAA() {
        logger.info("action AA");
    }

    public void actionAAA() {
        logger.info("action AAA");
    }
}
