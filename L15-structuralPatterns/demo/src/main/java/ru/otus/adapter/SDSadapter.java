package ru.otus.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SDS-адаптер.
 */
public class SDSadapter implements SDSdril {
    private static final Logger logger = LoggerFactory.getLogger(SDSadapter.class);
    private final Drill drill;

    public SDSadapter(Drill drill) {
        this.drill = drill;
    }

    @Override
    public void action() {
        // Адаптер не содержит логики
        // Он делегирует работу адаптируемым классам
        logger.info("{}", this.drill);
    }
}
