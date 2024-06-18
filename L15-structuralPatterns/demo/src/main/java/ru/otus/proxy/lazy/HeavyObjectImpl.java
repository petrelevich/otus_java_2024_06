package ru.otus.proxy.lazy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeavyObjectImpl implements HeavyObject {
    private static final Logger logger = LoggerFactory.getLogger(HeavyObjectImpl.class);
    private String value;
    private boolean isInit;

    @Override
    public void init(String value) {
        this.value = value;
        isInit = true;
        logger.info("heavy long initialization...");
    }

    @Override
    public boolean isInit() {
        return isInit;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "HeavyObject{" + "value='" + value + '\'' + '}';
    }
}
