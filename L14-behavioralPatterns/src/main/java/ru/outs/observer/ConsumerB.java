package ru.outs.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerB {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerB.class);

    /*
     * Большой и жирный класс со множеством полей
     *
     */

    private static final Listener listener = data -> logger.info("ConsumerB data:{}", data);

    public Listener getListener() {
        return listener;
    }
}
