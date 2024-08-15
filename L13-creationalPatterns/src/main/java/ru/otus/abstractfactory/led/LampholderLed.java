package ru.otus.abstractfactory.led;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.abstractfactory.Lampholder;

/**
 * @author sergey created on 18.09.18.
 */
public class LampholderLed implements Lampholder {
    private static final Logger logger = LoggerFactory.getLogger(LampholderLed.class);

    @Override
    public void hold() {
        logger.info("Led hold");
    }
}
