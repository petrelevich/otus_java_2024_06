package ru.outs.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerA implements Listener {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerA.class);

    @Override
    public void onUpdate(String data) {
        logger.info("ConsumerA data:{}", data);
    }
}
