package ru.otus.abstractfactory.led;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sergey created on 18.09.18.
 */
public class BulbLed implements ru.otus.abstractfactory.Bulb {
    private static final Logger logger = LoggerFactory.getLogger(BulbLed.class);

    @Override
    public void light() {
        logger.info("Led light");
    }
}
