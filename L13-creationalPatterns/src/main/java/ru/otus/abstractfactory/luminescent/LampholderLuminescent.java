package ru.otus.abstractfactory.luminescent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.abstractfactory.Lampholder;

/**
 * @author sergey created on 18.09.18.
 */
public class LampholderLuminescent implements Lampholder {
    private static final Logger logger = LoggerFactory.getLogger(LampholderLuminescent.class);

    @Override
    public void hold() {
        logger.info("Luminescent hold");
    }
}
