package ru.otus.logging.ex01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("java:S125")
public class HelloLogging {
    private static final Logger logger = LoggerFactory.getLogger(HelloLogging.class);

    public static void main(String[] args) {
        new HelloLogging().log();
    }

    private void log() {
        var value = "test";

        /*      Устаревший вариант
                if (logger.isDebugEnabled()) {
                    logger.debug("Hello logging:" + value);
                }
        */

        // Современный вариант
        logger.debug("Hello logging:{}", value);
        logger.atInfo()
                .setMessage("Hello logging atInfo:{}")
                .addArgument(() -> value)
                .log();

        try {
            throw new IllegalStateException("exception for log");
        } catch (Exception e) {
            logger.error("exception log:", e);
        }
    }
}
