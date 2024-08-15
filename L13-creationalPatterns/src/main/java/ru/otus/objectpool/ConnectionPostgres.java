package ru.otus.objectpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionPostgres implements Connection {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionPostgres.class);

    @Override
    public void connect() {
        logger.info("start connect...");
        sleep(2000);
        logger.info("connected");
    }

    @Override
    public void execSelect() {
        logger.info("run SELECT");
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
