package ru.otus.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HellSystemB {
    private static final Logger logger = LoggerFactory.getLogger(HellSystemB.class);

    public void actionB() {
        logger.info("action B");
    }

    public void actionBB() {
        logger.info("action BB");
    }

    public void actionBBB() {
        logger.info("action BBB");
    }
}
