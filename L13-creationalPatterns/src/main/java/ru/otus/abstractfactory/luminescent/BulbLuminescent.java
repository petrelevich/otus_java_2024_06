package ru.otus.abstractfactory.luminescent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.abstractfactory.Bulb;

/**
 * @author sergey created on 17.09.18.
 */
public class BulbLuminescent implements Bulb {
    private static final Logger logger = LoggerFactory.getLogger(BulbLuminescent.class);

    @Override
    public void light() {
        logger.info("Luminescent light");
    }
}
