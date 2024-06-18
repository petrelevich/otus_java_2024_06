package ru.outs.chain;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    private final List<String> history = new ArrayList<>();

    void addHistoryRecord(String historyRecord) {
        history.add(historyRecord);
    }

    void printHistory() {
        logger.info("{}", history);
    }
}
